package com.sicau.dao;


import com.sicau.entity.dto.Project;
import com.sicau.entity.dto.Team;
import com.sicau.entity.dto.Undertake;
import com.sicau.entity.pojo.po.TeamUndertakePO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UndertakeMapper {
    int insert(Undertake record);

    int insertSelective(Undertake record);
    /**
     * @param runId 进行项目Id
     * @return com.sicau.entity.dto.Undertake
     * @author Lee
     * @description 获取Undertake
     * @date 2019/2/8
     */
    Undertake getUndertake(@Param("runId") String runId);

    Undertake selectUndertakeById(@Param("undertakeId") String undertakeId);

    boolean updateUndertakeTeam(@Param("undertakeId") String undertakeId,@Param("state") String state);

    boolean insertUndertake(@Param("undertake") Undertake undertake);

    List<String> selectProjectIdByState(@Param("state") String state);

    List<String> selectTeamIdByProjectId(@Param("projectId") String projectId,@Param("state") String state);

    TeamUndertakePO selectTeamState(@Param("teamId") String teamId, @Param("projectId") String projectId, @Param("state") String state);

    int selectUndertakeByTeamId(@Param("teamId") String teamId);

    void deletByProjectId(@Param("projectId") String projectId);

    int selectUndertakeId();
    String selectFunctionList(@Param("projectId") String projectId,@Param("teamId") String teamId);
}