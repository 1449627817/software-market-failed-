package com.sicau.entity.dto;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 0:42 2019/2/18
 **/
public class MessageAndUser {

    private int id;
    private String massageId;
    private String userSend;
    private String userGet;

    public MessageAndUser(int id, String massageId, String userSend, String userGet) {
        this.id = id;
        this.massageId = massageId;
        this.userSend = userSend;
        this.userGet = userGet;
    }

    public MessageAndUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMassageId() {
        return massageId;
    }

    public void setMassageId(String massageId) {
        this.massageId = massageId;
    }

    public String getUserSend() {
        return userSend;
    }

    public void setUserSend(String userSend) {
        this.userSend = userSend;
    }

    public String getUserGet() {
        return userGet;
    }

    public void setUserGet(String userGet) {
        this.userGet = userGet;
    }
}
