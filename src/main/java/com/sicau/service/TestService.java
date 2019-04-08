package com.sicau.service;

import com.sicau.entity.dto.Delay;
import com.sicau.entity.pojo.vo.ResultVO;

/**
 * Created by wzw on 2019/1/26
 *
 * @Author wzw
 */
public interface TestService {
    /**
     * 登录验证
     * @return ResultVO
     */
    ResultVO login();


    ResultVO xt_test();
}
