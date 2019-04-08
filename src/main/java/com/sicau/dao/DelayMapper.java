package com.sicau.dao;

import com.sicau.entity.dto.Delay;
import com.sicau.entity.pojo.po.DelayExamplePO;
import com.sicau.entity.pojo.po.DelayPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DelayMapper {
    long countByExample(DelayExamplePO example);

    int deleteByExample(DelayExamplePO example);

    int deleteByPrimaryKey(String delayId);

    int insert(Delay delay);

    int insertSelective(Delay delay);

    List<Delay> selectByExample(DelayExamplePO example);

    Delay selectByPrimaryKey(String delayId);

    int updateByExampleSelective(@Param("record") Delay record, @Param("example") DelayExamplePO example);

    int updateByExample(@Param("record") Delay record, @Param("example") DelayExamplePO example);

    int updateByPrimaryKeySelective(Delay record);

    int updateByPrimaryKey(Delay record);
    /**
     * @param delayId:延期项目ID
     * @return java.lang.String
     * @author Lee
     * @description ：查询数据库中的延期项目审核状态
     * @date 2019/2/7
     */
    String getState(@Param("delayId") String delayId);

    /**
     * 获取延期申请
     * @return
     */
    List<Delay> getAllDelay();

    int insertRunAndDelay(String runId,String delayId);

    List<DelayPO> getAllDelayWithTeam();
}