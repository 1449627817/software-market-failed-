package com.sicau.entity.dto;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 0:41 2019/2/18
 **/
public class MessageAndType {

    private int id;

    private String messageId;

    private String typeId;

    public MessageAndType(int id, String messageId, String typeId) {
        this.id = id;
        this.messageId = messageId;
        this.typeId = typeId;
    }

    public MessageAndType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
