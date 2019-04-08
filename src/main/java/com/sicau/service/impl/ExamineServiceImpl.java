package com.sicau.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sicau.dao.*;
import com.sicau.entity.dto.*;
import com.sicau.entity.pojo.po.AuditedTeamPO;
import com.sicau.entity.pojo.po.AuditedTeamsPO;
import com.sicau.entity.pojo.po.ProjectUndertakePO;
import com.sicau.entity.pojo.po.TeamUndertakePO;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.util.IDUtil;
import com.sicau.util.VeDate;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sicau.enums.ResultEnum;
import com.sicau.service.ExamineService;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ExamineServiceImpl implements ExamineService{
	@Autowired
	private DelayMapper delayMapper;
	@Autowired
	private ProjectMapper projectMapper;
	@Autowired
	private ModificationProjectMapper modificationProjectMapper;
	@Autowired
	private TeamMapper teamMapper;
	@Autowired
	private UndertakeMapper undertakeMapper;
	@Autowired
	private ModificationTeamMapper modificationTeamMapper;
	@Autowired
	private  UserMapper userMapper;
	@Autowired
	private  TeamAndUserMapper teamAndUserMapper;

    /**
     * 项目延时审核
     *
     * @Param:delayId，延时项目的id
     * @Param:state，延时项目的延时审核状态 根据delayId查出的数据，把查出数据的state赋值为参数state
     * 若无次delayId，则返回参数错误(-1)
     * 有，则赋值并返回成功状态码(0)
     * @author hyc
     */
    @Override
    public Map<String, Object> delayByDelayId(String delayId, String state) {
        Map<String, Object> map = new HashMap<String, Object>();
        Delay delay = delayMapper.selectByPrimaryKey(delayId);
        ResultEnum result = null;
        String msg = null;
        if (delayId.equals("-1") || delay == null) {
            result = ResultEnum.PARAM_ERROR;
        } else {
            delay.setState(state);
            delayMapper.updateByPrimaryKey(delay);
            result = ResultEnum.SUCCESS;
        }
        map.put("status", result.getCode());
        map.put("msg", result.getMessage());
        return map;
    }

    /**
     * 信推办发起项目修改
     * 修改前，先判断有无此项目id，
     * 若有此id，则进行修改，返回修改成功状态码（0）
     * 若无此id，返回参数错误状态码（-1）
     *
     * @param projectId 待修改项目id
     * @param
     * @author hyc
     */
    @Override
    public Map<String, Object> modificateProjectByX(String projectId, String projectName, String projectRequirement,
                                                    String projectTime, String projectDescribe, String projectPrice) {
        Map<String, Object> map = new HashMap<String, Object>();
        ResultEnum result = null;
        if (projectMapper.selectByPrimaryKey(projectId) != null) {
            Project project = new Project();
            project.setProjectId(projectId);
            project.setProjectDescribe(projectDescribe);
            project.setProjectName(projectName);
            project.setProjectPrice(projectPrice);
            project.setProjectRequirement(projectRequirement);
            project.setProjectTime(projectTime);
            projectMapper.updateByPrimaryKey(project);
            result = ResultEnum.SUCCESS;
        } else {
            result = ResultEnum.PARAM_ERROR;
        }

        map.put("status", result.getCode());
        map.put("msg", result.getMessage());
        return map;
    }

    /**
     * 项目修改审核
     * 修改待修改项目的审核状态
     *
     * @param modificationId 要修改审核状态的项目修改id
     * @param state          指定要修改的审核状态
     * @author hyc
     */
    @Override
    public Map<String, Object> updateModificationProjectState(String modificationId, String state) {
        Map<String, Object> map = new HashMap<String, Object>();
        ModificationProject mp = modificationProjectMapper.selectByPrimaryKey(modificationId);
        ResultEnum result = null;
        if (mp != null) {
            mp.setState(state);
            modificationProjectMapper.updateByPrimaryKey(mp);
            result = ResultEnum.SUCCESS;
        } else {
            result = ResultEnum.PARAM_ERROR;
        }
        map.put("status", result.getCode());
        map.put("msg", result.getMessage());
        return map;
    }

    /**
     * 获取待审核团队
     * 通过team表的state来查询待审核团队
     *
     * @author hyc
     */
    @Override
    public Map<String, Object> getUnauditedTeams() {
        ResultEnum result = null;
        Map<String, Object> map = new HashMap<String, Object>();
        List<AuditedTeamPO> ats = teamMapper.selectAuditedTeam();
        List<AuditedTeamsPO> teams = new ArrayList<AuditedTeamsPO>();
        if (ats != null) {
            AuditedTeamsPO team = new AuditedTeamsPO();
            String teamId = null;
            for (AuditedTeamPO at : ats) {
                //不是同一支队伍时
                if (!at.getTeam().getTeamId().equals(teamId) || teamId == null) {
                    teams.add(team);
                    team.setTeam(at.getTeam());
                    team.setCaptain(at.getCaptain());
                }
                team.getMemberPOS().add(at.getMemberPO());
                teamId = at.getTeam().getTeamId();
            }
            result = ResultEnum.SUCCESS;
            map.put("status", result.getCode());
            map.put("msg", result.getMessage());
            map.put("data", teams);
        }
        return map;
    }

    @Override
    public ResultVO getModificationProject() {
        ResultVO resultVO = new ResultVO();
        List<ModificationProject> modificationProjects = new ArrayList<>();
        modificationProjects = modificationProjectMapper.getAll();
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("modificationProject", modificationProjects);
        if (modificationProjects != null) {
            resultVO.setStatus(ResultEnum.SUCCESS.getCode());
            resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
            resultVO.setData(jsonObject);
        } else {
            resultVO.setStatus(ResultEnum.PARAM_ERROR.getCode());
            resultVO.setMsg(ResultEnum.PARAM_ERROR.getMessage());
        }
        return resultVO;
    }

    /**
     * Description:审核项目
     *
     * @author yj
     * CreateTime 22:10 2019/2/13
     **/
    @Override
    public ResultVO projectAudit(String projectId, String state) {
        //判断传入字符串是否为空
        if ("".equals(projectId.trim()) && "".equals(state.trim())) {
            // 用户传入的项目id和审核状态不能为空字符
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        } else {
            try {
                // 执行查询操作，查询project表中项目是否存在
                Project projectDTO = projectMapper.selectProjectById(projectId);
                System.out.printf("===================" + projectDTO);
                if (projectDTO == null) {
                    System.out.printf("===================" + projectId);
                    return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), ResultEnum.RESOURCE_NOT_FOUND.getMessage());
                } else {
                    //项目存在，则更新state和项目发布时间
                    String data = VeDate.getStringDate();
                    boolean hasUpdate = projectMapper.updateStateById(projectId, state, data);
                    if (hasUpdate) {
                        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
                    } else {
                        return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
                    }
                }
            } catch (Exception e) {
                // 打印异常堆栈信息
                e.printStackTrace();
                // 一旦抛出异常就返回未知错误信息
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
            }
        }
    }

    /**
     * Description:团队审核
     *
     * @author yj
     * CreateTime 23:25 2019/2/13
     **/
    @Override
    public ResultVO teamAudit(String teamId, String state) {
        //判断传入字符串是否为空
        if ("".equals(teamId.trim()) && "".equals(state.trim())) {
            // 用户传入的团队id和审核状态不能为空字符
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        } else {
            try {
                // 执行查询操作，查询team表中团队是否存在
                Team team = teamMapper.selectTeamById(teamId);
                if (team == null) {
                    return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), ResultEnum.RESOURCE_NOT_FOUND.getMessage());
                } else {
                    //团队存在，更新团队状态参数
                    boolean hasUpdata = teamMapper.updateStateTeam(teamId, state);
                    if (hasUpdata) {
                        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
                    } else {
                        return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), "更新失败");
                    }
                }
            } catch (Exception e) {
                // 打印异常堆栈信息
                e.printStackTrace();
                // 一旦抛出异常就返回未知错误信息
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
            }
        }
    }

    /**
     * Description:审核项目分配
     *
     * @author yj
     * CreateTime 23:53 2019/2/13
     **/
    @Override
    public ResultVO projectAllocation(String undertakeId, String state) {
        //判断传入字符串是否为空
        if ("".equals(undertakeId.trim()) && "".equals(state.trim())) {
            // 用户传入的承接id和审核状态不能为空字符
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        } else {
            try {
                // 执行查询操作，查询undertake表中承接是否存在
                Undertake undertake = undertakeMapper.selectUndertakeById(undertakeId);
                if (undertake == null) {
                    return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), ResultEnum.RESOURCE_NOT_FOUND.getMessage());
                } else {
                    //承接存在，更新承接的状态参数
                    boolean hasUpdate = undertakeMapper.updateUndertakeTeam(undertakeId, state);
                    if (hasUpdate) {
                        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
                    } else {
                        return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), "更新失败");
                    }
                }
            } catch (Exception e) {
                // 打印异常堆栈信息
                e.printStackTrace();
                // 一旦抛出异常就返回未知错误信息
                return new ResultVO(ResultEnum.UNKNOWN_ERROR.getCode(), ResultEnum.UNKNOWN_ERROR.getMessage());
            }
        }
    }

    /**
     * Description:获取待审核项目分配（哪些团队接哪些项目）
     *
     * @author yj
     * CreateTime 00:14 2019/2/14
     **/
    @Override
    @Transactional
    public ResultVO projectGetAllocation() {
        String state = "1";
        ArrayList<ProjectUndertakePO> projectUndertakePOList = new ArrayList<>();
        // 执行查询操作，查询undertake表中审核状态未审核的承接，输出承接的项目id
        List<String> projectId = undertakeMapper.selectProjectIdByState(state);
        for (int i = 0; i < projectId.size(); i++) {
            ArrayList<TeamUndertakePO> teamUndertakePOList = new ArrayList<>();
            System.out.printf("---------------" + projectId.get(i) + "---------------");
            //查询项目基本信息
            Project project = projectMapper.selectProjectById(projectId.get(i));
            if (project == null) {
                //若project==null，说明该项目已被删除，删除undertaken表里有关该项目的信息
                undertakeMapper.deletByProjectId(projectId.get(i));
            }else {
                ProjectUndertakePO projectUndertakePO = new ProjectUndertakePO(project.getProjectId(), project.getProjectTime(), project.getProjectName(), project.getProjectRequirement(), project.getProjectDescribe(), project.getProjectPrice(), project.getReleaseTime(), project.getUserId());
                //执行查询操作，根据projectId查询undertake表中承接的团队id
                List<String> teamId = undertakeMapper.selectTeamIdByProjectId(projectId.get(i), state);
                for (int j = 0; j < teamId.size(); j++) {
                    //获取团队信息
                    TeamUndertakePO teamUndertakePO = undertakeMapper.selectTeamState(teamId.get(j), projectId.get(i), state);
                    teamUndertakePOList.add(teamUndertakePO);
                }
                //把团队信息加入至项目信息
                projectUndertakePO.setTeamUndertakePOList(teamUndertakePOList);
                projectUndertakePOList.add(projectUndertakePO);
            }
        }
        if (projectUndertakePOList == null) {
            return new ResultVO(ResultEnum.RESOURCE_NOT_FOUND.getCode(), "没有待审核承接");
        } else {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectUndertakePOList);
        }
    }

    @Override
    public ResultVO getAllProject() {
        try {
            List<Project> projects = projectMapper.selectAll();
            if (projects != null) {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projects);
            } else {
                return new ResultVO(0, "无待审核项目");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO updateModificationTeamState(String modificationId, String state) {
        ModificationTeam result = modificationTeamMapper.selectByPrimaryKey(modificationId);
        ResultVO resultVO = new ResultVO();
        if (result != null) {
            ModificationTeam modificationTeam = new ModificationTeam();
            modificationTeam.setModificationId(modificationId);
            modificationTeam.setState(state);
            modificationTeamMapper.updateStateByPrimaryKey(modificationTeam);
            resultVO.setStatus(ResultEnum.SUCCESS.getCode());
            resultVO.setMsg(ResultEnum.SUCCESS.getMessage());
        } else {
            resultVO.setStatus(ResultEnum.RESOURCE_NOT_FOUND.getCode());
            resultVO.setMsg(ResultEnum.RESOURCE_NOT_FOUND.getMessage());
        }
        return resultVO;
    }

    @Override
    public ResultVO createTeamApplication(ModificationTeam modificationTeam) {
        modificationTeamMapper.insert(modificationTeam);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    @Override
    public ResultVO createProjectApplication(ModificationProject modificationProject) {
        modificationProjectMapper.insert(modificationProject);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    @Override
    @Transactional
    public ResultVO updateTeamMessage(Team team) {
        teamMapper.updateScore(team.getTeamId(), team.getTeamScore());
        teamMapper.updateStateTeam(team.getTeamId(), team.getTeamState());
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    @Override
    public ResultVO getTeamModificationMessage() {
        String unAudited = "1";
        List<ModificationTeam> modificationTeamList = modificationTeamMapper.selectByState(unAudited);
        JSONObject jsonObject = new JSONObject();
        jsonObject.element("modificationTeam", modificationTeamList);
        return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), jsonObject);
    }

    /**
     * @param state 支持可变长参数state查询
     * @return com.sicau.entity.pojo.vo.ResultVO 列表
     * @Description:项目根据状态分类
     * @author cxh
     * @date 2019/3/9
     **/
    @Override
    public ResultVO projectsClassifyByState(String... state) {
        try {
            List<Project> projectList = projectMapper.projectsClassifyByState(state);
            System.out.println(projectList);
            if (projectList.isEmpty()) {
                System.out.println("该状态项目查询结果为空！");
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), "结果为空");
            } else {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), projectList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
    }

    @Override
    public ResultVO addTeammate(String teamId,User user) {
        if(teamId==null||user==null){
            return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
        }
        try {
            String captainId=teamAndUserMapper.selectCaptainId(teamId);
            String userId=userMapper.selectUserId(user.getName(),user.getStudentId());
            if(captainId==null||userId==null){
                return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
            }
            TeamAndUser teamAndUser = new TeamAndUser(teamId, captainId, userId);
//            String direction=userMapper.getDirectionId(user.getDirection());
//            user.setDirection(direction);
            if (teamAndUserMapper.addTeammate(teamAndUser) == 1) {
                return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
            } else {
                return new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
            }
        } catch (Exception e) {
            ResultVO resultVO = new ResultVO(ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
            e.printStackTrace();
            return resultVO;
        }
    }

    @Override
    public ResultVO deleteTeammate(String userId, String teamId) {
        if (teamAndUserMapper.deleteByTeamIdAndUserId(teamId, userId) == 1) {
            return new ResultVO(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
        } else {
            throw new RuntimeException();
        }
    }
}
