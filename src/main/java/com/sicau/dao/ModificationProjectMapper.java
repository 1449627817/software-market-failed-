package com.sicau.dao;

import com.sicau.entity.dto.ModificationProject;

import java.util.List;

public interface ModificationProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table modification_project
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    int deleteByPrimaryKey(String modificationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table modification_project
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    int insert(ModificationProject record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table modification_project
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    ModificationProject selectByPrimaryKey(String modificationId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table modification_project
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    List<ModificationProject> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table modification_project
     *
     * @mbg.generated Fri Feb 01 16:05:07 CST 2019
     */
    int updateByPrimaryKey(ModificationProject record);
    /**
     * @Description: 获取全部项目信息修改请求
     * @return: java.util.List<com.sicau.entity.dto.ModificationProject>
     * @Author: Lee
     * @Date: 2019/2/4
     */
    List<ModificationProject> getAll();
}