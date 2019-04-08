package com.sicau.entity.dto;

/**
 * Description:
 * @author tzw
 * CreateTime 0:33 2019/2/18
 **/
public class UserAndIdentity {

    private int id;
    private String userId;
    private String IdentityId;

    public UserAndIdentity(int id, String userId, String identityId) {
        this.id = id;
        this.userId = userId;
        IdentityId = identityId;
    }

    public UserAndIdentity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIdentityId() {
        return IdentityId;
    }

    public void setIdentityId(String identityId) {
        IdentityId = identityId;
    }
}
