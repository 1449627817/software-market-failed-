package com.sicau.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicau.constants.MessageConstants;
import com.sicau.dao.*;
import com.sicau.entity.dto.*;
import com.sicau.entity.pojo.po.TeamPO;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ResultEnum;
import com.sicau.service.CollectService;
import com.sicau.service.SuperviseService;
import com.sicau.util.IDUtil;
import com.sicau.util.VeDate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;

    @Autowired
    private UndertakeMapper undertakeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SuperviseService superviseService;

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private TeamMapper teamMapper;

   @Override
    public ResultVO collectUndertake(Undertake undertake){
        try {
            undertake.setState("1");
            collectMapper.updateUndertake(undertake);
            return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("处理失败，返回失败结果");
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO collectNeed(Project project){
        try {
            System.out.println("开始处理");
            //设置id为uuid
            project.setProjectId(UUID.randomUUID().toString().replaceAll("-",""));
            collectMapper.updateProject(project);
            System.out.println("处理完成");
            return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("处理失败，返回失败结果");
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO collectTeam(TeamPO teamPO){
        try {
            System.out.println("开始处理");
            //设置id为uuid
            teamPO.setTeamId(IDUtil.getUUID());
            //查询队长信息
            // 将字符串转换为json数组
            JSONObject captainObject = JSONObject.fromObject(teamPO.getCaptainInformation());
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //将json字符串转化为User对象
            User captain = mapper.readValue(captainObject.toString(), User.class);
            //查询
            String captainId = collectMapper.selectUserId(captain);
            System.out.println("队长id为"+captainId);
            if (captainId==null || captainId==""){
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),"查无"+captain.getName()+captain.getStudentId()+"的队长");
            }

            //查询队员信息
            JSONArray memberArray = JSONArray.fromObject(teamPO.getMemberInformation());
            //每一次循环都找到一个队员，并且将相关信息加入到teamAndUser表中
            for(int i = 0;i<memberArray.size();i++){

                User member = mapper.readValue(memberArray.getJSONObject(i).toString(), User.class);
                //查询
                String memberId = collectMapper.selectUserId(member);
                if (memberId==null || memberId==""){
                    return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),"查无"+member.getName()+member.getStudentId()+"的队员");
                }
                TeamAndUser teamAndUser = new TeamAndUser(teamPO.getTeamId(),captainId,memberId);
                collectMapper.updateTeamAndUser(teamAndUser);
            }

            //将团队信息加入team表中
            teamPO.setTeamState("1");
            teamPO.setCreateTime(new SimpleDateFormat("yyyy.MM.dd").format(new Date()));
            //teamPO.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

            Integer teamNumber = memberArray.size()+1;
            teamPO.setNumber(teamNumber.toString());
            collectMapper.updateTeam(teamPO);
            System.out.println("团队信息添加成功");
            return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("处理失败，返回失败结果");
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),e.toString());
        }
    }

    /**
     * @Describe 开发团队参与项目征集
     * @author yj
     * @return 状态值
     */
    @Override
    public ResultVO projectTeamSolicitation(Undertake undertake) {
        try {
            //团队申请承接项目时间
            String date = VeDate.getStringDate();
            //存入对象
            undertake.setTime(date);
            boolean hasInsert = undertakeMapper.insertUndertake(undertake);
            if (hasInsert){
                Project project = projectMapper.selectByPrimaryKey(undertake.getProjectId());
                Team team = teamMapper.selectTeamById(undertake.getTeamId());
                int id = undertakeMapper.selectUndertakeId();
                String content = "尊敬的推信办老师："
                                      +team.getTeamName()+"正在申请"+project.getProjectName()+",等待您审批中";
                superviseService.sendMessage(content,"6",MessageConstants.pushLetterToId,MessageConstants.systemId,MessageConstants.teamAndProject,String.valueOf(id));
                if (team!=null&&project!=null){
                    return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
                }else {
                    return new ResultVO(ResultEnum.DATABASE_ERROR.getCode(),ResultEnum.DATABASE_ERROR.getMessage());
                }
            }else {
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),"插入记录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO selectAllTeamDirection() {
        try{
            List<DirectionName> directionNameList = collectMapper.selectAllTeamDirection();
            return new ResultVO(ResultEnum.SUCCESS.getCode(),"返回团队方向成功",directionNameList);
        }catch (Exception e){
            e.printStackTrace();
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(),ResultEnum.PARAM_ERROR.getMessage());
            
        }
    }

    @Override
    public ResultVO selectAllUserDirection() {
        try {
            List<UserDirection> userDirectionList = userMapper.selectAllUsersDirection();
            if (userDirectionList!=null){
                return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),userDirectionList);
            }else {
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),"失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(),ResultEnum.UNKNOWN_ERROR.getMessage());
        }

    }

    @Override
    public ResultVO add(User user) {
        user.setUserId(IDUtil.getUUID());
        userMapper.insert(user);
        return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
    }

    @Override
    public ResultVO ifNew(String studentId){
        if(collectMapper.selectUserByStudentId(studentId)==null){
            return new ResultVO(ResultEnum.USER_NOT_FOUND.getCode(),ResultEnum.USER_NOT_FOUND.getMessage());
        }
        return new ResultVO(ResultEnum.USER_EXIST.getCode(),ResultEnum.USER_EXIST.getMessage());
    }
}
