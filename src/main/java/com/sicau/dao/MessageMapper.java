package com.sicau.dao;

import com.sicau.entity.dto.Message;
import com.sicau.entity.pojo.po.MessagePo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 12:37 2019/2/26
 **/
public interface MessageMapper {

    String  selectContentById(@Param("messageId") String messageId);

    String selectUserSendById(@Param("messageId") String messageId);

    String selectMessageIdByUserGet(@Param("userGet") String userGet);

    Message selectById(@Param("messageId") String messageId);

    List<Message> selectAllMessage();

    List<MessagePo> selectMessagePO();

    MessagePo selectMessagePoById(@Param("messageId") String messageId);

    List<MessagePo> selectMessagePOByUserGet(String userGet);

    int updateState(String messageId);
}
