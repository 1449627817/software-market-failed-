package com.sicau.dao;


import com.sicau.entity.dto.RunProject;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;

public interface RunProjectMapper {
    int insert(RunProject record);

    int insertSelective(RunProject record);
    /**
     * @param runId 进行项目Id
     * @param score 分数
     * @return int 返回更新影响的行数
     * @author Lee
     * @description 更新项目的分数
     * @date 2019/2/8
     */
    int updateScore(@Param("runId") String runId, @Param("score") String score);
    /**
     * 暂时未用
     */
    int addTimeNode(@Param("timeNode")String timeNode,@Param("runId")String runId);

    /**
     * 获取项目当前进度
     * @param runId
     * @return
     */
    String selectProgress(@Param("runId") String runId);

    /**
     * 通过主键获取进行的项目
     * @param runId
     * @return
     */
    RunProject selectByRunId(@Param("runId") String runId);

    /**
     * 加入进行的项目
     * @param runProject
     * @return
     */
    int addRunProject(RunProject runProject);

    /**
     * 更新项目当前进度（进入下一节点）
     * @param runProject
     * @return
     */
    int updateProgress(RunProject runProject);

    //String selectTeamByIdAndRunProjectId(@Param("time") String time,@Param("startTime") String startTime);
    String selectUndertakeIdByRunId(@Param("runId") String runId);
    String selectOverTime(@Param("runId") String runId);

    String selectTeamIdByRunIdWithUnderTake(@Param("runId") String runId);

    /**
     * 根据runId获取时间节点
     * @param runId
     * @return
     */
    String selectTimeNode(String runId);
}