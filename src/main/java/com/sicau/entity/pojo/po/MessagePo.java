package com.sicau.entity.pojo.po;

/**
 * 监督和评价系统的融合了message 、 messageAndUser的实体类
 * @author ttxxi
 */
public class MessagePo {
    private String messageId;
    private String state;
    private String createTime;
    private String topic;
    private String userGet;
    private String content;
    private String userSend;

    public MessagePo(String messageId, String state, String createTime, String topic, String userGet, String content, String userSend) {
        this.messageId = messageId;
        this.state = state;
        this.createTime = createTime;
        this.topic = topic;
        this.userGet = userGet;
        this.content = content;
        this.userSend = userSend;
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUserGet() {
        return userGet;
    }

    public void setUserGet(String userGet) {
        this.userGet = userGet;
    }

    @Override
    public String toString() {
        return "MessagePo{" +
                "messageId='" + messageId + '\'' +
                ", messageState='" + state + '\'' +
                ", createTime='" + createTime + '\'' +
                ", topic='" + topic + '\'' +
                ", userGet='" + userGet + '\'' +
                ", content='" + content + '\'' +
                ", userSend='" + userSend + '\'' +
                '}';
    }
}
