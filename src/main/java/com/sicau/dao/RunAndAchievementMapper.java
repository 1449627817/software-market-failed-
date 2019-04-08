package com.sicau.dao;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wzw on 2019/3/17
 *
 * @Author wzw
 */
public interface RunAndAchievementMapper {
    String getAchievementId(String runId);
    String selectAchievementFile(@Param("timeNode")String timeNode,@Param("runId") String runId);
    List<String> selectAchievementId(@Param("runId") String runId);
}
