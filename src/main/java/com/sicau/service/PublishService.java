package com.sicau.service;


import com.sicau.entity.pojo.vo.ResultVO;

/**
 * @author ttxxi
 * write on 2019-2-12
 * 发布系统服务的接口
 */
public interface PublishService {

    /**
     * 根据某个条件查询团队
     * @param condition 条件，可以为direction、state、teamId 分别对应前端对应前端 /teambydirection   /teambystate    /team
     * @param value 具体的值
     * @return
     */
    ResultVO publishTeamBy(String condition ,String value);

    /**
     * 根据某个条件查询团队
     * @param projectId 条件，可以为 0  -1  其他
     * @return 查询到的结果
     */
    ResultVO publishProject(String projectId);

    /**
     * 根据状态查询团队
     * @param state
     * @return
     */
    ResultVO publishProjectByState(String state);

}
