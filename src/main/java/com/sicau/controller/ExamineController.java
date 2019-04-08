package com.sicau.controller;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sicau.constants.CommonConstants;
import com.sicau.entity.dto.ModificationProject;
import com.sicau.entity.dto.ModificationTeam;
import com.sicau.entity.dto.Team;
import com.sicau.entity.dto.User;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ExamineTypeEnum;
import com.sicau.enums.ResultEnum;
import com.sicau.service.DelayService;
import com.sicau.service.ExamineService;
import com.sicau.util.IDUtil;
import com.sicau.util.MD5Util;
import net.sf.json.JSONObject;
import org.apache.ibatis.session.SqlSessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:審核controller
 * @author yj
 * CreateTime 22:01 2019/2/13
 **/
@RestController
@RequestMapping(CommonConstants.NONPUBLIC_PREFIX+"/examine")
public class ExamineController {
	
	@Autowired
	private ExamineService examineService;
	@Autowired
	private DelayService delayService;

	@ResponseBody
	@RequestMapping(value ="/delay", method = {RequestMethod.POST})
	public Map<String, Object> delay(@RequestBody Map<String,String> map1) {
		String delayId = map1.get("delayId");
		String state = map1.get("state");
		Map<String, Object>map = examineService.delayByDelayId(delayId, state);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/modificationProjectX", method = {RequestMethod.POST})
	public Map<String, Object> modificationProjectX(@RequestBody Map<String,String> map1) {
		String projectId = map1.get("projectId");
		String projectName = map1.get("projectName");
		String projectRequirement = map1.get("projectRequirement");
		String projectDescribe = map1.get("projectDescribe");
		String projectPrice = map1.get("projectPrice");
		String projectTime = map1.get("projectTime");
		Map<String, Object> map = examineService.modificateProjectByX(projectId, projectName, projectRequirement, projectTime, projectDescribe, projectPrice);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/modificationProjectJudge", method = RequestMethod.POST)
	public Map<String, Object> modificationProjectJudge(@RequestBody Map<String,String> map1) {
		String modificationId = map1.get("modificationId");
		String state = map1.get("state");
		Map<String, Object> map = examineService.updateModificationProjectState(modificationId, state);
		return map;
	}

	@ResponseBody
	@RequestMapping(value = "/get/team", method = RequestMethod.GET)
	public Map<String, Object> getTeams(){
		Map<String, Object>map = examineService.getUnauditedTeams();
		System.out.println("========================map:" + map);
		return map;
	}
	/**
	 * @return com.sicau.entity.pojo.vo.ResultVO
	 * @author Lee
	 * @description :获取全部的项目修改请求以及内容
	 * @date 2019/2/7
	 */
	@ResponseBody
	@RequestMapping(value="/get/modificationProject",method = RequestMethod.GET)
	public ResultVO getModificationProject(){
		return examineService.getModificationProject();
	};

    /**
     * @Describe 审核项目
	 * @author yj
     * @return ResultVO 测试成功
     */
	@ResponseBody
    @RequestMapping(value = "/project",method = {RequestMethod.POST})
    public ResultVO projectAudit(@RequestBody Map<String,String> map){
		String projectId = map.get("projectId");
		String state = map.get("state");
        return examineService.projectAudit(projectId,state);
    }

    /**
     * @Describe 审核团队
	 * @author yj
     * @return ResultVO  测试成功
     */
	@ResponseBody
    @RequestMapping(value = "/team",method = {RequestMethod.POST})
    public ResultVO teamAudit(@RequestBody Map<String,String> map){
		String teamId = map.get("teamId");
		String state = map.get("state");
        return examineService.teamAudit(teamId,state);
    }
    /**
     * @Describe 审核项目分配
	 * @author yj
     * @return ResultVO  测试成功
     */
    @RequestMapping(value = "/allocation",method = {RequestMethod.POST})
    public ResultVO projectAllocation(@RequestBody Map<String,String> map){
		String undertakeId = map.get("undertakeId");
		String state = map.get("state");
        return examineService.projectAllocation(undertakeId,state);
    }

    /**
     * @Describe 获取待审核项目分配（哪些团队接哪些项目）
	 * @author yj
     * @return ResultVO 已修改并测试成功
     */
    @RequestMapping(value = "/get/allocation",method = {RequestMethod.GET})
    public ResultVO projectGetAllocation(){
    	try{
			return examineService.projectGetAllocation();
		}catch (RuntimeException e){
    		return new ResultVO(ResultEnum.DATABASE_ERROR.getCode(),ResultEnum.DATABASE_ERROR.getMessage());
		}
    }


	/**
	 * Description:获取待审核项目
	 * @author tzw
	 * CreateTime 21:11 2019/2/7
	 **/
    @RequestMapping("/get/project")
	public ResultVO getProject()
	{
          return examineService.getAllProject();
	}

	/**
	 * 获取延期申请
	 * @return
	 */
	@RequestMapping(value = "/get/delay",method = RequestMethod.GET)
	public ResultVO getDelay() {
		return delayService.getAllDelay();
	}

	/**
	 * 团队修改审核
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/modificationTeamJudge", method = RequestMethod.POST)
	public ResultVO modificationTeamJudge(@RequestBody Map<String,String> map) {
		String modificationId = map.get("modificationId");
		String state = map.get("state");
		ResultVO resultVO = examineService.updateModificationTeamState(modificationId, state);
		return resultVO;
	}

	/**
	 *发起团队修改
	 * @param map 修改的信息
	 * @return
	 */
	@RequestMapping(value = "/modificationTeam", method = RequestMethod.POST)
	public ResultVO modificationTeam(@RequestBody Map<String, Object> map) {
		ModificationTeam modificationTeam = new ModificationTeam();
		String teamId = map.get("teamId").toString();
		String modificationType = map.get("modificationType").toString();
		modificationTeam.setTeamId(teamId);
		modificationTeam.setModificationType(modificationType);
		List<String>modificationPlace = (ArrayList<String>)map.get("modificationPlace");

		JSONObject originalData = new JSONObject();
		originalData = JSONObject.fromObject(map.get("originalData").toString());

		JSONObject modificationContent = new JSONObject();
		modificationContent = JSONObject.fromObject(JSONObject.fromObject(map.get("modificationContent").toString()));

		ResultVO resultVO = null;
		for(int i = 0; i < modificationPlace.size(); i++){
			modificationTeam.setModificationContent(modificationContent.get(modificationPlace.get(i).toString()).toString());
			modificationTeam.setOriginalData(originalData.get(modificationPlace.get(i).toString()).toString());
			modificationTeam.setModificationId(IDUtil.getUUID());
			modificationTeam.setState("1");
			modificationTeam.setModificationPlace(modificationPlace.get(i).toString());
			try{
				resultVO = examineService.createTeamApplication(modificationTeam);
			}catch (SqlSessionException e){
				resultVO.setStatus(-1);
				resultVO.setMsg("添加数据失败，请重试");
				return resultVO;
			}
		}
		return resultVO;
	}

	/**
	 * 发起项目修改
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/modificationProject", method = RequestMethod.POST)
	public ResultVO modificationProject(@RequestBody Map<String,String> map) {
		String projectId = map.get("projectId");
		String modificationType = map.get("modificationType");
		String modificationContent = map.get("modificationContent");
		String originalData = map.get("originalData");
		String modificationPlace = map.get("modificationPlace");
		ModificationProject modificationProject = new ModificationProject();
		modificationProject.setProjectId(projectId);
		modificationProject.setState(ExamineTypeEnum.WAIT.getMsg());
		modificationProject.setModificationContent(modificationContent);
		modificationProject.setModificationType(modificationType);
		modificationProject.setOriginalData(originalData);
		modificationProject.setModificationPlace(modificationPlace);
		modificationProject.setModificationId(IDUtil.getUUID());
		ResultVO resultVO = examineService.createProjectApplication(modificationProject);
		return resultVO;
	}

	/**
	 * 团队修改（信推办）
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/modificationTeamX", method = RequestMethod.POST)
	public ResultVO modificationTeamX(@RequestBody Map<String,String> map) {
		String teamId = map.get("teamId");
		String teamScore = map.get("teamScore");
		String teamState = map.get("teamState");
		Team team = new Team();
		team.setTeamId(teamId);
		team.setTeamState(teamState);
		team.setTeamScore(teamScore);
		try {
			return examineService.updateTeamMessage(team);
		}catch (RuntimeException e){
			return new ResultVO(ResultEnum.DATABASE_ERROR.getCode(),ResultEnum.DATABASE_ERROR.getMessage());
		}
	}

	/**
	 * 获取团队信息修改请求
	 * @return
	 */
	@RequestMapping(value = "/get/modificationTeam", method = RequestMethod.GET)
	public ResultVO getModificationTeam() {
		ResultVO resultVO = examineService.getTeamModificationMessage();
		return resultVO;
	}

	/**
	 * @Description:项目根据状态分类
	 * @param state 支持可变长参数state查询
	 * @return com.sicau.entity.pojo.vo.ResultVO 列表
	 * @author cxh
	 * @date 2019/3/9
	 **/
	@RequestMapping(value = "/projectsClassifyByState",method = RequestMethod.GET)
	public ResultVO projectsClassifyByState(@RequestParam("state") String... state){
		return examineService.projectsClassifyByState(state);
	}
	/**
	 * @return com.sicau.entity.pojo.vo.ResultVO
	 * @author Lee
	 * @description 添加成员
	 * @date 2019/3/12
	 */
	@RequestMapping(value ="/addTeammate",method = RequestMethod.POST)
	public ResultVO addTeammate(@RequestBody Map<String,String> map){
		User user=new User();
	    String teamId=map.get("teamId").toString();
		user.setName(map.get("name").toString());
//		user.setDirection(map.get("direction"));
//		user.setDepartment(map.get("department").toString());
//		user.setMajor(map.get("major").toString());
//		user.setGrade(map.get("grade").toString());
//		user.setSex(map.get("sex").toString());
//		user.setDescription(map.get("description").toString());
//		user.setTelNumber(map.get("telephone").toString());
		user.setStudentId(map.get("studentId").toString());
		return examineService.addTeammate(teamId,user);
	}
	/**
	 * @return com.sicau.entity.pojo.vo.ResultVO
	 * @author Lee
	 * @description 删除成员
	 * @date 2019/3/12m
	 */
	@RequestMapping(value = "/deleteTeammate",method = RequestMethod.POST)
	public ResultVO deleteTeammate(@RequestBody Map<String,String> map){
		String teamId=map.get("teamId").toString();
		String userId=map.get("userId").toString();
		try{
			return examineService.deleteTeammate(userId,teamId);
		}catch (RuntimeException e){
			return new ResultVO( ResultEnum.PARAM_ERROR.getCode(), ResultEnum.PARAM_ERROR.getMessage());
		}
	}
}
