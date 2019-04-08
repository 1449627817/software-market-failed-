package com.sicau.service;

import com.sicau.entity.pojo.vo.ResultVO;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: software-market
 * @description: 监督和评价系统
 * @author: Lee
 * @create: 2019-02-07 21:40
 **/
public interface SuperviseService {
    /**
     * @param runID:进行的项目ID
     * @param score:分数
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description 给项目打分
     * @date 2019/2/7
     */
    ResultVO scoring(String runID, String score);

    /**
     * @param delayID:延迟申请ID
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description 获取项目的延期审核状态
     * @date 2019/2/7
     */
    ResultVO getDelayState(String delayID);

    /**
     * Description:查看审核状态
     * @author tzw
     * CreateTime 15:03 2019/2/15
     **/
    ResultVO getUndertakeState(String undertake_id);

    /**
     * 创建时间表
     * @param timeNode
     * @return
     */
    ResultVO createTimeTable(String timeNode);

    /**
     * 获取队伍当前进度
     * @param runId
     * @return
     */
    ResultVO getProgress(String runId);

    ResultVO timeoutJudgement(String runId);

    ResultVO changeProcess(String runId);

    ResultVO getMessageByMessageId(String messageId);

    ResultVO getMessageByUserGet(String userGet);

    /**
     * 列入黑名单
     * @param teamId
     * @return
     */
    ResultVO addBlackList(String teamId);

    ResultVO judge(String runId);
    ResultVO sendMessage(String content, String messageType, String userGet, String userSend, String messageTopic,String relation);

    /**
     * @return ResultVO
     * @author Lee
     * @description 下载项目成果
     * @date 2019/3/6
     */
    ResultVO downloadAchievement(String timeNode, String runId, HttpServletResponse httpServletResponse);

    /**
     * @return ResultVO
     * @author Lee
     * @description 下载项目计划
     * @date 2019/3/6
     */
    ResultVO downloadProjectDocument(String teamId, String projectId, HttpServletResponse httpServletResponse);

    /**
     * @return ResultVO
     * @author Lee
     * @description 下载团队信息
     * @date 2019/3/6
     */
    ResultVO downloadTeamMsg(String fileFunction, String teamId, HttpServletResponse httpServletResponse);

    /**
     * 获取全部时间节点
     * @param runId
     * @return
     */
    ResultVO allTimeNodes(String runId);

    /**
     * 获取全部审核记录
     * @param runId
     * @param timeNode
     * @return
     */
    ResultVO getAllHistory(String runId, String timeNode);

    ResultVO changeMessageState(String messageId);

    ResultVO getRelationProject(String messageId);

    ResultVO getTeamState(String teamId);
    /**
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description :获取消息类型
     * @date 2019/3/22
     */
    ResultVO getMessageType(String messageId);

    ResultVO projectState(String projectId);

    ResultVO getProjectByProgress(String progress);
}
