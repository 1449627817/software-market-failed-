package com.sicau.dao;


import com.sicau.entity.dto.*;
import com.sicau.entity.pojo.po.TeamPO;

import java.util.List;

/**
 * @author ttxxi write on 2019-02-12
 * 发布系统的Dao层接口
 */
public interface PublishMapper {

    /**
     * 根据方向去team表获取团队
     */
    List<Team> selectTeamByDirection(String dir);

    /**
     * 根据团队id 去teamAndUser表查记录
     * @param id 团队id
     * @return 查询到的记录
     */
    List<TeamAndUser> selectTeamAndUserById(String id);

    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return 查询到的用户
     */
    User selectUserById(String id);

    /**
     * 根据团队的state 去team表查找团队
     * @param state 状态
     * @return 查到的所有团队
     */
    List<Team> selectTeamByState(String state);

    /**
     * 查询全部的团队信息
     * @return 查到的所有团队
     */
    List<Team> selectAllTeam();


    /**
     * 根据团队的teamId 去team表查找团队
     * @param teamId 团队的teamId
     * @return 查到的团队
     */
    Team selectTeamByTeamId(String teamId);



    /**
     * 查询所有的project
     * @return 查到的所有project
     */
    List<Project> selectAllProject();

    /**
     * 根据id查询project
     * @param id projectId
     * @return 查到的project
     */
    Project selectProjectById(String id);

    /**
     * 根据团队id去undertake表查询相关项目承接信息
     * @param projectId
     * @return
     */
    List selectUndertakeByProjectIdState2(String projectId);

    /**
     * 根据团队id去undertake表查询相关项目承接信息
     * @return
     */
    List selectUndertakeByProjectIdState1();

    /**
     * 根据teamId去work表查对应团队的所有project
     * @param TeamId
     * @return
     */
    List<String> selectWorksByTeamId(String TeamId);

    /**
     *根据状态查询项目
     * @param state
     * @return
     */
    List<Project> selectProjectByState(String state);

}
