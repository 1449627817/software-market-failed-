package com.sicau.service.impl;

import com.sicau.dao.*;
import com.sicau.entity.dto.*;
import com.sicau.entity.pojo.po.MessagePo;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ResultEnum;
import com.sicau.service.SuperviseService;
import com.sicau.util.DownloadUtil;
import com.sicau.util.IDUtil;
import com.sicau.util.VeDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: software-market
 * @description:
 * @author: Lee
 * @create: 2019-02-07 21:42
 **/
@Service
public class SuperviseServiceImpl implements SuperviseService {
    @Autowired
    RunProjectMapper runProjectDao;
    @Autowired
    TeamMapper teamDao;
    @Autowired
    UndertakeMapper undertakeDao;
    @Autowired
    DelayMapper delayDao;
    @Autowired
    MessageMapper messageMapper;
    @Autowired
    MessageTypeMapper messageTypeMapper;
    @Autowired
    private ProjectMapper projectMapper;
    @Autowired
    private RunAndAchievementMapper runAndAchievementMapper;
    @Autowired
    private AchievementsMapper achievementsMapper;
    @Autowired
    private MessageAndTypeMapper messageAndTypeMapper;

    @Override
    public ResultVO scoring(String runID, String score) {
        ResultVO resultVO = new ResultVO();
        try {
            int Rresult = runProjectDao.updateScore(runID, score);
            if (Rresult == 1) {
                String undertakeId = runProjectDao.selectUndertakeIdByRunId(runID);
                Undertake undertake = undertakeDao.getUndertake(undertakeId);
                Team team = teamDao.getTeam(undertake.getTeamId());
                //将Team中原来的分数和新的分数进行相加，再和团队项目数相除，获取新的Team分数
                Float finaScore = Float.parseFloat(team.getTeamScore()) + (Float.parseFloat(score) / Float.parseFloat(team.getNumber()));
                int result = teamDao.updateScore(undertake.getTeamId(), finaScore.toString());
                if (result == 1) {
                    resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
                    resultVO.setStatus(ResultEnum.SUCCESS.getCode());
                } else {
                    resultVO.setMsg(ResultEnum.PARAM_ERROR.getMessage());
                    resultVO.setStatus(ResultEnum.PARAM_ERROR.getCode());
                }
            } else {
                resultVO.setMsg(ResultEnum.PARAM_ERROR.getMessage());
                resultVO.setStatus(ResultEnum.PARAM_ERROR.getCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultVO.setMsg(ResultEnum.PARAM_ERROR.getMessage());
            resultVO.setStatus(ResultEnum.PARAM_ERROR.getCode());
        }
        return resultVO;
    }

    @Override
    public ResultVO getDelayState(String delayID) {
        ResultVO resultVO = new ResultVO();
        String state = delayDao.getState(delayID);
        if (state != null) {
            resultVO.setData(delayDao.getState(delayID));
            resultVO.setStatus(ResultEnum.SUCCESS.getCode());
            resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
        } else {
            resultVO.setStatus(ResultEnum.PARAM_ERROR.getCode());
            resultVO.setMsg(ResultEnum.PARAM_ERROR.getMessage());
        }
        return resultVO;
    }

    @Override
    public ResultVO getUndertakeState(String undertake_id) {
        try {
            Undertake undertake = undertakeDao.selectUndertakeById(undertake_id);
            return new ResultVO(ResultEnum.SUCCESS.getCode(), "查看审核状态成功", undertake.getState());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), "查看审核状态出错");
        }

    }

    @Override
    public ResultVO createTimeTable(String timeNode) {
        timeNode = timeNode.substring(0, timeNode.length() - 1);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        RunProject runProject = new RunProject();
        runProject.setTimeNode(timeNode);
        runProject.setDelayTime("0");
        runProject.setStartTime(String.valueOf(df.format(new Date())));
        runProject.setOvertime("0");
        runProject.setScore("0");
        runProject.setRunId(IDUtil.getUUID());
        String[] progress = timeNode.split(";");
        runProject.setProgress(progress[0]);
        runProjectDao.addRunProject(runProject);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    @Override
    public ResultVO getProgress(String runId) {
        String result = runProjectDao.selectProgress(runId);
        if (result == null) {
            return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), ResultEnum.RESOURCE_NOT_FOUND.getMessage());
        }
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), result);
    }

    @Override
    public ResultVO timeoutJudgement(String runId) {
        String result = runProjectDao.selectProgress(runId);
        if (result == null) {
            return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), ResultEnum.RESOURCE_NOT_FOUND.getMessage());
        } else {
            String[] time = result.split(" ");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dt1 = new Date();
                Date dt2 = df.parse(time[1]);
                if (dt1.getTime() > dt2.getTime()) {
                    System.out.println("dt1 在dt2前");
                    return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), 1);
                }
            } catch (Exception exception) {
                exception.printStackTrace();
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage(), -1);
            }
        }
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), 0);
    }

    @Override
    public ResultVO changeProcess(String runId) {
        RunProject runProject = runProjectDao.selectByRunId(runId);
        String[] progress = runProject.getTimeNode().split(";");
        String nowProgress = runProject.getProgress();
        String[] node = nowProgress.split(" ");
        try {
            runProject.setProgress(progress[Integer.parseInt(node[0])]);
        } catch (Exception exception) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), "已完成项目");
        }
        runProjectDao.updateProgress(runProject);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    @Override
    public ResultVO getMessageByMessageId(String messageId) {
        try {
            System.out.println("------------------------------------" + messageId);
            if (messageId.equals("0")) {
                List<MessagePo> messages = messageMapper.selectMessagePO();
                return new ResultVO(ResultEnum.SUCCESS.getCode(), "获取消息", messages);
            } else {
                MessagePo message = messageMapper.selectMessagePoById(messageId);
                List<MessagePo> messages = new ArrayList<>();
                messages.add(message);
                return new ResultVO(ResultEnum.SUCCESS.getCode(), "获取消息", messages);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), "获取消息失败");
        }
    }

    @Override
    public ResultVO getMessageByUserGet(String userGet) {
        try {
            List<MessagePo> messagePos = messageMapper.selectMessagePOByUserGet(userGet);
            return new ResultVO(ResultEnum.SUCCESS.getCode(), "获取消息成功", messagePos);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(-1, "获取消息失败");
        }
    }

    @Override
    public ResultVO addBlackList(String teamId) {
        try {
            if (teamDao.selectTeamById(teamId) == null) {
                System.out.println("该团队不存在，无法列入黑名单！");
                return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
            } else {
                String state = "4";
                teamDao.updateStateTeam(teamId, state);
                return new ResultVO(ResultEnum.SUCCESS.getCode(), "列入黑名单成功！");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO judge(String runId) {
        try {
            String overTime = runProjectDao.selectOverTime(runId);
            if (overTime.equals("3")) {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), "要加入黑名单", 1);
            } else {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), "未超过限制超时次数，不加入黑名单", 0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(-1, "未知错误");
        }


    }

    @Override
    @Transactional
    public ResultVO sendMessage(String content, String messageTypeId, String userGet, String userSend, String messageTopic, String relation) {
        //消息初始查看状态为未查看
        String messageState = "1";
        //记录message的id
        String messageId = IDUtil.getUUID();
        //消息创建时间
        String createTime = VeDate.getStringDate();
        //对message进行封装
        Message message = new Message(messageId, messageState, createTime, content, messageTopic, relation);
        //判断messageType消息类型的id是否存在数据库中
        System.out.printf("=============" + messageTypeId + "-------------------");
        MessageType messageType = messageTypeMapper.selectMessageTypeById(messageTypeId);
        //若messageType不为空，则存在表中，对messageAndType和MessageAndType表、message表添加对应信息
        if (messageType != null) {
            //对message进行插入
            boolean hasInsert = messageTypeMapper.insertMessage(message);
            if (hasInsert) {
                //对messageAndType表添加对应信息
                boolean hasMessageAndType = messageTypeMapper.insertMessageAndType(messageId, messageTypeId);
                //对MessageAndUser表添加对应信息
                boolean hasMessageUser = messageTypeMapper.insertMessageAndUser(messageId, userSend, userGet);
                //判断是否插入成功
                if (hasMessageAndType && hasMessageUser) {
                    return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
                } else {
                    return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
                }
            } else {
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), "消息类型参数错误");
            }
        } else {
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
        }

    }

    @Override
    public ResultVO downloadAchievement(String timeNode, String runId, HttpServletResponse httpServletResponse) {
        DownloadUtil downloadUtil = new DownloadUtil();
        List<String> achievementIdList=runAndAchievementMapper.selectAchievementId(runId);
        String filename=achievementsMapper.selectAchievementContent(achievementIdList,timeNode);
        String url = "achievement/" + runId + "/" + filename;
        return downloadUtil.downLoad(url,filename,httpServletResponse);
    }

    @Override
    public ResultVO downloadProjectDocument(String teamId, String projectId, HttpServletResponse httpServletResponse) {
        DownloadUtil downloadUtil = new DownloadUtil();
        String filename=undertakeDao.selectFunctionList(projectId,teamId);
        String url = "projectApplicationDocument/" + teamId + "/" + filename;
        return downloadUtil.downLoad(url,filename,httpServletResponse);
    }

    @Override
    public ResultVO downloadTeamMsg(String fileFunction, String teamId, HttpServletResponse httpServletResponse) {
        DownloadUtil downloadUtil = new DownloadUtil();
        String filename=teamDao.selectTeamFile(teamId,fileFunction);
        System.out.println(fileFunction+"====================");
        String url = "teamDocument/" + teamId + "/" + filename;
        return downloadUtil.downLoad(url,filename,httpServletResponse);
}

    @Override
    public ResultVO allTimeNodes(String runId) {
        String node = runProjectDao.selectTimeNode(runId);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), node);
    }

    @Override
    public ResultVO getAllHistory(String runId, String timeNode) {
        String achievementsId = runAndAchievementMapper.getAchievementId(runId);
        Achievements achievements = achievementsMapper.selectByRunIdAndNode(achievementsId, timeNode);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), achievements);
    }

    @Override
    public ResultVO changeMessageState(String messageId) {
        try {
            int result = messageMapper.updateState(messageId);
            if (result == 1) {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
            } else {
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
        }


    }

    @Override
    public ResultVO getRelationProject(String messageId) {
        try {
//            String messageType=messageTypeMapper.selectMessageTypeById(messageId);
            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO getTeamState(String teamId) {
        try {
            String state = teamDao.getTeamStateByTeamId(teamId);
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), state);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO getMessageType(String messageId) {
        String messageType = messageAndTypeMapper.selectMessageType(messageId);
        if (messageType != null) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), messageType);
        } else {
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());

        }
    }

    @Override
    public ResultVO projectState(String projectId) {
        try {
            Project project = projectMapper.selectProjectById(projectId);
            if (project != null) {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), project);
            } else {
                return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), ResultEnum.RESOURCE_NOT_FOUND.getMessage());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), e.getMessage());
        }
    }

    @Override
    public ResultVO getProjectByProgress(String progress) {
        try {
            if (progress.equals("已完成")) {
                List<Project> projectList1 = projectMapper.selectCompleteProject();
                if (projectList1.isEmpty()) {
                    System.out.println("没有已经完成的项目！");
                }
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectList1);
            } else if (progress.equals("")) {
                List<Project> projectList2 = projectMapper.selectUnUnderTakeProject();
                if (projectList2.isEmpty()) {
                    System.out.println("没有未承接的项目（待使用）！");
                }

                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectList2);
            } else if(progress.equals("未承接")){
                List<Project> projectList5 = projectMapper.selectWaitUnderTakeProject();
                if(projectList5.isEmpty()){
                    System.out.println("没有未承接的项目");
                }
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectList5);
            } else if(progress.equals("0")){
                List<Project> projectList4 = projectMapper.selectAll();
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectList4);
            } else if (!progress.equals("已完成")) {
                List<Project> projectList3 = projectMapper.selectUnCompleteProject();
                if (projectList3.isEmpty()) {
                    System.out.println("没有项目正在进行中！");
                }
                
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectList3);
            }
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());

        }
    }
}
