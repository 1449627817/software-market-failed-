package com.sicau.dao;

import com.sicau.entity.dto.Message;
import com.sicau.entity.dto.MessageType;
import org.apache.ibatis.annotations.Param;

/**
 * Description:審核controller
 * @author yj
 * CreateTime 19:50 2019/2/26
 **/

public interface MessageTypeMapper {

    boolean insertMessageAndType(@Param("messageId") String messageId,@Param("messageTypeId") String messageTypeId);

    boolean insertMessageAndUser(@Param("messageId") String messageId,@Param("userSend") String userSend,@Param("userGet") String userGet);

    boolean insertMessageType(@Param("messageTypeId") String messageTypeId,@Param("messageType")  String messageType);

    boolean insertMessage(@Param("messagePO") Message messagePO);

    MessageType selectMessageTypeById(@Param("messageType") String messageType);
}
