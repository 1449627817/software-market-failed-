package com.sicau.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Description:发布Dao层
 * @author yj
 * CreateTime 22:13 2019/2/13
 **/
public interface ReleaseMapper {
    //ReleaseDTO selectReleaseByProjectId(@Param("projectId") String projectId);

    boolean updateReleaseByProjectId(@Param("projectId") String projectId, @Param("state") String state, @Param("data") String data);

    //boolean insertRelease(@Param("releaseDTO") ReleaseDTO releaseDTO);
}
