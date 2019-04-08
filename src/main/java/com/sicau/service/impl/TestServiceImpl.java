package com.sicau.service.impl;

import com.sicau.dao.TestMapper;
import com.sicau.entity.dto.Delay;
import com.sicau.entity.pojo.vo.ResultVO;
import com.sicau.enums.ResultEnum;
import com.sicau.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wzw on 2019/1/26
 *
 * @Author wzw
 */
@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestMapper testDao;
    @Override
    public ResultVO login() {
        //testDao.selectAll();
        return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage());
    }

    @Override
    public ResultVO xt_test(){

        return new ResultVO(ResultEnum.SUCCESS.getCode(),ResultEnum.SUCCESS.getMessage(),testDao.selectDelay());
    }
}
