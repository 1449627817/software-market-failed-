package com.sicau.dao;

import org.apache.ibatis.annotations.Param;

/**
 * Created by wzw on 2019/2/26
 *
 * @Author wzw
 */
public interface MessageAndTypeMapper {
    /**
     * 根据主键获取消息类型
     * @param messageId
     * @return
     */
    String selectMessageType(@Param("messageId") String messageId);
}
