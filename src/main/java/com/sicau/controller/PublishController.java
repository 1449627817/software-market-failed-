package com.sicau.controller;


import com.sicau.constants.CommonConstants;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ttxxi
 * write on 2019-2-12
 * 发布系统
 *
 */
@RestController
@RequestMapping(value=CommonConstants.NONPUBLIC_PREFIX+"/publish")
public class PublishController {

    @Autowired
    private PublishService ps;

    /**
     *
     * @param direction 传参过来的   方向  用来查询团队
     * @return 查询结果
     */
    @RequestMapping("/teambydirection")
    public ResultVO direction(String direction){

        return ps.publishTeamBy("direction",direction);
    }

    @RequestMapping("/teambystate")
    public ResultVO state(String teamState){
        return ps.publishTeamBy("state",teamState);
    }

    @RequestMapping("/team")
    public ResultVO team(String teamId,String teamCategory){
        return ps.publishTeamBy(teamId,teamCategory);
    }

    @RequestMapping("/project")
    public ResultVO project(String projectId){
        return ps.publishProject(projectId);
    }

    @RequestMapping("/projectbystate")
    public ResultVO projectBySatate(String state){
        return ps.publishProjectByState(state);
    }



}
