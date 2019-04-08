package com.sicau.entity.dto;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 0:38 2019/2/18
 **/
public class Message {

    private String messageId;
    private String state;
    private String createTime;
    private String content;
    private String topic;
    private String relation;

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    private String userSend;

    public Message(String messageId, String state, String createTime, String content,String topic,String relation) {
        this.messageId = messageId;
        this.state = state;
        this.createTime = createTime;
        this.content = content;
        this.topic=topic;
        this.relation = relation;
    }

    public Message() {
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserSend() {
        return userSend;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
