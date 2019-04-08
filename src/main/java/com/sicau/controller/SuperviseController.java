package com.sicau.controller;

import com.sicau.constants.CommonConstants;
import com.sicau.entity.dto.Delay;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ResultEnum;
import com.sicau.service.AchievementsService;
import com.sicau.service.DelayService;
import com.sicau.service.SuperviseService;
import com.sicau.util.IDUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @program: software-market
 * @description: 监督和评价系统
 * @author: Lee
 * @create: 2019-02-07 21:40
 **/
@RestController
@Slf4j
@RequestMapping(CommonConstants.NONPUBLIC_PREFIX+"/supervise")
public class SuperviseController {
    @Autowired
    SuperviseService superviseService;
    @Autowired
    private AchievementsService achievementsService;
    @Autowired
    private DelayService delayService;
    /**
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description 给项目打分
     * @date 2019/2/7
     */
    @RequestMapping(value ="/scoring",method = RequestMethod.POST)
    public ResultVO scoringProject(@RequestBody Map<String,String> map){
        String runID=map.get("runId").toString();
        String score=map.get("score").toString();
        return superviseService.scoring(runID,score);
    }
    /**
     * @param delayId:延迟的项目ID
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description:获取项目的延迟审核状态
     * @date 2019/2/7
     */
    @RequestMapping(value ="/delayState",method = RequestMethod.GET)
    public ResultVO getDealyState(@RequestParam("delayId") String delayId){
        System.out.println(superviseService.getDelayState(delayId));
        return  superviseService.getDelayState(delayId);
    }

    /**
     * Description:查看审核状态
     * @author tzw
     * CreateTime 1:38 2019/2/15
     **/
   @RequestMapping("/state")
    public ResultVO getUndertakeState(@RequestParam("undertakeId") String undertakeId)
   {
       return superviseService.getUndertakeState(undertakeId);
   }

    /**
     *
     * @param runId 正在运行的项目id
     * @param timeNode 时间节点
     * @param achievementType 成果类型
     * @param achievement 成果文件
     * @return
     * @author hyc
     */
    @RequestMapping("/achievements")
    private ResultVO commitAchievement(@RequestParam("runId") String runId,
                                       @RequestParam("timeNode") String timeNode,
                                       @RequestParam("achievementType") String achievementType,
                                       @RequestParam("achievement") MultipartFile achievement)
    {
        return achievementsService.commit(runId, timeNode, achievement);

    }

    /**
     * 延期申请
     * @param map
     * @return
     */
    @RequestMapping(value = "/delay",method = RequestMethod.POST)
    public ResultVO addDelay(@RequestBody Map<String,String> map)
    {
        try{
            String delayTime=map.get("delayTime");
            String runId=map.get("runId");
            Delay delay = new Delay();
            delay.setState("1");
            delay.setDelayId(IDUtil.getUUID());
            delay.setDelayTime(delayTime);
            return delayService.addDelay(delay,runId);
        }catch (Exception e){
            return new ResultVO(ResultEnum.DATABASE_ERROR.getCode(),ResultEnum.DATABASE_ERROR.getMessage());
        }
    }

    /**
     * 生成运行时项目并创建时刻表
     * @param map
     * @return
     */
    @RequestMapping(value = "/timetable",method = RequestMethod.POST)
    public ResultVO makeRunProject(@RequestBody Map<String,String> map){
////        Gson gson = new Gson();
////        List<TimeNodePO> timeNodePOList = new ArrayList<>();
////        try{
////            timeNodePOList =gson.fromJson(timeNode,
////                    new TypeToken<List<TimeNodePO>>(){
////                    }.getType());
////        }catch (Exception e){
////            log.error("【json转换】错误，string={}",timeNode);
////            return new ResultVO(-1,"【json转换】错误");
////        }
//        if(timeNode==null){
//            return new ResultVO(10,"资源不存在");
//        }
//        String result = new String();
//        try {
//            JSONArray jsonArray = JSONArray.fromObject(timeNode);
//            for (int i=0;i<jsonArray.size();i++){
//                JSONObject jsonObject= (JSONObject)jsonArray.get(i);
//                //将对应的字段整合成一个字符串
//                result = new StringBuilder().append(result).append(jsonObject.getString("number"))
//                        .append(" ").append(jsonObject.getString("time")).append(" ")
//                        .append(jsonObject.getString("things"))
//                        .append(";").toString();
//            }
//        } catch (Exception e){
////            log.error("【json转换】错误，string={}",timeNode);
//            return new ResultVO(-1,"【json转换】错误");
//        }
        String result=map.get("timeNode");
        return superviseService.createTimeTable(result);
    }

    /**
     * 项目进展查看
     * @param runId
     * @return
     */
    @RequestMapping(value = "/progress",method = RequestMethod.GET)
    public ResultVO progress(@RequestParam(required = true)String runId){
        return superviseService.getProgress(runId);
    }

    /**
     * 超时判断
     * @param map
     * @return
     */
    @RequestMapping(value = "/timeoutJudgement",method = RequestMethod.POST)
    public ResultVO timeoutJudgement(@RequestBody Map<String,String> map){
        String runId=map.get("runId");
        return superviseService.timeoutJudgement(runId);
    }
    /**
     * 超时判断
     * @param map
     * @return
     */
    @RequestMapping(value = "/changeProcess",method = RequestMethod.POST)
    public ResultVO changeProcess(@RequestBody Map<String,String> map){
        String runId=map.get("runId");
        return superviseService.changeProcess(runId);
    }

    /**
     * Description:根据消息id获取消息
     * @author tzw
     * CreateTime 12:31 2019/2/26
     **/
    @RequestMapping(value = "/getMessageByMessageId",method = RequestMethod.GET)
    public ResultVO getMessageByMessageId(@RequestParam("messageId") String messageId)
    {
        System.out.println("====================================="+messageId);
        return superviseService.getMessageByMessageId(messageId);
    }

    /**
     * Description:根据接收方获取信息
     * @author tzw
     * CreateTime 18:45 2019/2/26
     **/
    @RequestMapping(value = "/getMessageByUserGet",method = RequestMethod.GET)
    public ResultVO getMessageByUserGet(@RequestParam("userGet") String userGet)
    {
        return superviseService.getMessageByUserGet(userGet);
    }

    /**
     * @Description:列入黑名单
     * @param map
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author cxh 测试成功
     * @date 2019/3/4
     **/
    @RequestMapping(value = "/addBlacklist",method = RequestMethod.POST)
    public ResultVO addBlackList(@RequestBody Map<String,String> map){
        String teamId=map.get("teamId");
        return superviseService.addBlackList(teamId);
    }

    /**
     * @Describe 发送信息
     * @author yj
     * @return ResultVO 以修改部分并测试成功
     */
    @RequestMapping(value ="/sendMessage",method = RequestMethod.POST)
    public ResultVO sendMessage(@RequestBody Map<String,String> map){
        try {
            String content=map.get("content");
            String messageType=map.get("messageType");
            String userGet=map.get("userGet");
            String userSend=map.get("userSend");
            String messageTopic=map.get("messageTopic");
            String relation=map.get("relation");
            return superviseService.sendMessage(content,messageType,userGet,userSend,messageTopic,relation);
        }catch (RuntimeException e){
            return new ResultVO(ResultEnum.DATABASE_ERROR.getCode(),ResultEnum.DATABASE_ERROR.getMessage());
        }

    }

    /**
     * Description:根据接收方获取信息
     * @author tzw
     * CreateTime 00:26 2019/3/6
     **/
    @RequestMapping(value ="/blacklistJudgement",method = RequestMethod.GET)
    public ResultVO blacklistJudgement(@RequestParam("runId") String runId)
    {
        return superviseService.judge(runId);
    }


    /**
     * @return ResultVO
     * @author Lee
     * @description 下载项目成果
     * @date 2019/3/6
     */
    @RequestMapping(value ="/downloadAchievement",method = RequestMethod.GET)
    public ResultVO downloadAchievement(@RequestParam("timeNode") String timeNode, @RequestParam("runId") String runId, HttpServletResponse httpServletResponse) {
        return superviseService.downloadAchievement(timeNode, runId, httpServletResponse);
    }

    ;

    /**
     * @return ResultVO
     * @author Lee
     * @description 下载项目计划
     * @date 2019/3/6
     */
    @RequestMapping(value ="/downloadProjectDocument",method = RequestMethod.GET)
    public ResultVO downloadProjectDocument(@RequestParam("teamId") String teamId, @RequestParam("projectId") String projectId, HttpServletResponse httpServletResponse) {
        return superviseService.downloadProjectDocument(teamId, projectId, httpServletResponse);
    }

    /**
     * @return ResultVO
     * @author Lee
     * @description 下载团队信息
     * @date 2019/3/6
     */
    @RequestMapping(value ="/downloadTeamMsg",method = RequestMethod.GET)
    public ResultVO downloadTeamMsg(@RequestParam("fileFunction") String fileFunction, @RequestParam("teamId") String teamId, HttpServletResponse httpServletResponse) {
        return superviseService.downloadTeamMsg(fileFunction, teamId, httpServletResponse);
    }
    @RequestMapping(value ="/allTimeNodes",method = RequestMethod.GET)
    public ResultVO allTimeNodes(String runId){
        return superviseService.allTimeNodes(runId);
    }

    /**
     * Description:改变消息状态
     * @author tzw
     * CreateTime 00:08 2019/3/17
     **/
//    @RequestMapping(value = "/changeMessageState",method = RequestMethod.POST)
//    public ResultVO changeMessageState(HttpServletRequest request)
//    {
//        String messageId=request.getParameter("messageId");
//        return superviseService.changeMessageState(messageId);
//    }
//    @RequestMapping(value = "/changeMessageState",method = RequestMethod.POST)
//    public ResultVO changeMessageState(@RequestBody Map<String,String> map)
//    {
//        String messageId=map.get("messageId").toString();
//        System.out.println("----------------------------"+messageId);
//        return superviseService.changeMessageState(messageId);
//    }
    @RequestMapping(value = "/changeMessageState",method = RequestMethod.POST)
    public ResultVO changeMessageState(@RequestBody Map<String,String> map)
    {
        String messageId=map.get("messageId").toString();
        System.out.println("----------------------------"+messageId);
        return superviseService.changeMessageState(messageId);
    }

    @RequestMapping(value ="/achievementHistory",method = RequestMethod.GET)
    public ResultVO achievementHistory(String runId, String timeNode){
        return superviseService.getAllHistory(runId,timeNode);
    }

    /**
     * Description:根据消息类型和消息Id获取关联的待审核项
     * @author tzw
     * CreateTime 21:31 2019/3/20
     **/
    @RequestMapping("/toBeAudited")
    public ResultVO toBeAudited(@RequestParam("messageId") String messageId)
    {
        return superviseService.getRelationProject(messageId);
    }

    /**
     * Description:根据消息类型和消息Id获取关联的待审核项
     * @author tzw
     * CreateTime 20:31 2019/3/21
     **/

    @RequestMapping("/teamState")
    public ResultVO teamState(@RequestParam("teamId") String teamId)
    {
        return superviseService.getTeamState(teamId);
    }
    /**
     * @return com.sicau.entity.pojo.vo.ResultVO
     * @author Lee
     * @description :获取消息类型
     * @date 2019/3/22
     */
    @RequestMapping(value = "/getMessageType")
    public ResultVO getMessageType(@RequestParam("messageId")String messageId){
        return superviseService.getMessageType(messageId);
    }

    /**
     * @Describe 查看项目审核状态
     * @author yj
     * @return ResultVO 以修改部分并测试成功
     */
    @RequestMapping(value = "/projectState")
    public ResultVO projectState(@RequestParam("projectId") String projectId){
        return superviseService.projectState(projectId);
    }

     /**
      * @Description:通过项目进程获取项目信息
      * @param
      * @param progress
      * @return com.sicau.entity.pojo.vo.ResultVO
      * @author cxh
      * @date 2019/3/24
      **/
    @RequestMapping(value = "/getProjectByProgress")
    public ResultVO getProjectByProgress(@RequestParam("progress") String progress){
        return superviseService.getProjectByProgress(progress);
    }


}
