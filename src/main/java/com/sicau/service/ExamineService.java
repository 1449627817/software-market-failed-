package com.sicau.service;

import com.sicau.entity.dto.ModificationProject;
import com.sicau.entity.dto.ModificationTeam;
import com.sicau.entity.dto.Team;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.entity.dto.User;

import java.util.Map;

/**
 * Description:方法
 * @author yj
 * CreateTime 22:09 2019/2/13
 **/

public interface ExamineService {
	public Map<String, Object>delayByDelayId(String delayId, String state);
	public Map<String, Object>modificateProjectByX(String projectId, String projectName, String projectRequirement, String projectTime, String projectDescribe, String projectPrice);
	public Map<String, Object>updateModificationProjectState(String modificationId, String state);
	public Map<String, Object> getUnauditedTeams();
	/**
	 * @return com.sicau.entity.pojo.vo.ResultVO
	 * @author Lee
	 * @description :获取全部修改项目的信息
	 * @date 2019/2/7
	 */
	ResultVO getModificationProject();

	ResultVO projectAudit(String projectId, String state);

	ResultVO teamAudit(String teamId, String state);

	ResultVO projectAllocation(String undertakeId, String state);

	ResultVO projectGetAllocation();

    ResultVO getAllProject();

	/**
	 * 审核团队信息修改请求
	 * @param modificationId
	 * @param state
	 * @return
	 */
	ResultVO updateModificationTeamState(String modificationId, String state);

	/**
	 *提交团队信息修改申请
	 * @param modificationTeam
	 * @return
	 */
	ResultVO createTeamApplication(ModificationTeam modificationTeam);

	/**
	 *提交项目信息修改申请
	 * @param modificationProject
	 * @return
	 */
	ResultVO createProjectApplication(ModificationProject modificationProject);

	/**
	 *信推办修改团队信息
	 * @param team
	 * @return
	 */
	ResultVO updateTeamMessage(Team team);

	/**
	 *获取团队修改请求列表
	 * @return
	 */
	ResultVO getTeamModificationMessage();
	/**
	 * @Description:项目根据状态分类
	 * @param state 支持可变长参数state查询
	 * @return com.sicau.entity.pojo.vo.ResultVO 列表
	 * @author cxh
	 * @date 2019/3/9
	 **/
    ResultVO projectsClassifyByState(String... state);
	/**
	 * @return com.sicau.entity.pojo.vo.ResultVO
	 * @author Lee
	 * @description 添加成员
	 * @date 2019/3/12
	 */
	ResultVO addTeammate(String teamId,User user);
	/**
	 * @return com.sicau.entity.pojo.vo.ResultVO
	 * @author Lee
	 * @description 删除成员
	 * @date 2019/3/12
	 */
	ResultVO deleteTeammate(String userId,String teamId);
}
