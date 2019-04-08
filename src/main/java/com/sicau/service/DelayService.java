package com.sicau.service;

import com.sicau.entity.dto.Delay;
import com.sicau.entity.dto.Identity;
import com.sicau.entity.dto.User;
import com.sicau.entity.pojo.vo.ResultVO;

import java.util.List;

/**
 * 〈延时申请〉
 * create by cxh on 2019/2/7
 */

public interface DelayService {
    /**
     * 延时申请
     * @param delay
     * @param runId
     * @return
     */
     ResultVO addDelay(Delay delay,String runId);

    /**
     * 获取延期申请
     * @return
     */
    ResultVO getAllDelay();
}
  
 
