package com.sicau.dao;

import com.sicau.entity.dto.Delay;
import com.sicau.entity.dto.Test;

import java.util.List;

/**
 * TestMapper
 * @author chenshihang on 2019/1/21
 */

public interface TestMapper {


    /**
     * 获取全部用户对象
     * @return List<user>
     */
    List<Test> selectAll();

    List<Delay> selectDelay();


    void updateDelay(Delay delay);
}
