package com.sicau.dao;

import com.sicau.entity.dto.TeamAndUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamAndUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamAndUser
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamAndUser
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    int insert(TeamAndUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamAndUser
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    TeamAndUser selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamAndUser
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    List<TeamAndUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table teamAndUser
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    int updateByPrimaryKey(TeamAndUser record);
    int deleteByTeamIdAndUserId(@Param("teamId") String teamId, @Param("userId") String userId);
    int addTeammate(TeamAndUser teamAndUser);
    String selectCaptainId(@Param("teamId") String teamId);
}