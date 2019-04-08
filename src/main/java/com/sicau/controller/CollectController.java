package com.sicau.controller;


import com.sicau.constants.CommonConstants;
import com.sicau.entity.dto.Project;
import com.sicau.entity.dto.Undertake;
import com.sicau.entity.dto.User;
import com.sicau.entity.pojo.po.TeamPO;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ttxxi
 * write on 2019-1-31
 * 征集系统的控制器
 */

@RestController
@RequestMapping(value=CommonConstants.NONPUBLIC_PREFIX+"/collect")
public class CollectController{

    @Autowired
    private CollectService collectService;

//    @RequestMapping("/project")
//    public ResultVO project(Undertake undertake){
//        System.out.println(undertake);
//        return collectService.collectUndertake(undertake);
//    }

    @RequestMapping("/need")
    public ResultVO need(Project nd){
        System.out.println(nd);
        return collectService.collectNeed(nd);
    }

    @RequestMapping("/team")
    public ResultVO team(TeamPO tp){
        System.out.println(tp);
        return collectService.collectTeam(tp);
    }

    /**
     * @Describe 开发团队参与项目征集
     * @author yj
     * @return ResultVO 测试成功
     */
    @RequestMapping("/project")
    public ResultVO projectTeamSolicitation(@RequestParam("projectId") String projectId,
                                            @RequestParam("teamId") String teamId,
                                            @RequestParam("timeNode") String timeNode,
                                            @RequestParam("functionList") String functionList){
        String state = "1";
        Undertake undertake = new Undertake(teamId,projectId,state,functionList,timeNode);
        return collectService.projectTeamSolicitation(undertake);
    }

    /**
     * @Description:获得所有可供选择团队方向
     * @return com.sicau.entity.pojo.vo.ResultVO 列表
     * @author cxh 测试成功
     * @date 2019/3/7
     **/
    @RequestMapping(value = "/selectAllTeamDirection",method = RequestMethod.GET)
    public ResultVO selectTeamDirection(){
        return collectService.selectAllTeamDirection();
    }

    /**
     * @Describe 获取全部用户方向列表
     * @author yj
     * @return ResultVO 测试成功
     */
    @RequestMapping("/selectAllUserDirection")
    public ResultVO selectAllUserDirection(){
        return collectService.selectAllUserDirection();
    }

    @RequestMapping(value = "user",method = RequestMethod.POST)
    public ResultVO getUser(User user){
        return collectService.add(user);
    }

    @RequestMapping(value = "/ifNew",method = RequestMethod.POST)
    public ResultVO idNewUser(String studentId){
        return collectService.ifNew(studentId);
    }
}
