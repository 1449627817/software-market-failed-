package com.sicau.entity.dto;

/**
 * Description:
 * @author tzw
 * CreateTime 0:35 2019/2/18
 **/
public class Authority {

    private String aauthorityId;
    private String aauthorityName;

    public Authority(String aauthorityId, String aauthorityName) {
        this.aauthorityId = aauthorityId;
        this.aauthorityName = aauthorityName;
    }

    public Authority() {
    }

    public String getAauthorityId() {
        return aauthorityId;
    }

    public void setAauthorityId(String aauthorityId) {
        this.aauthorityId = aauthorityId;
    }

    public String getAauthorityName() {
        return aauthorityName;
    }

    public void setAauthorityName(String aauthorityName) {
        this.aauthorityName = aauthorityName;
    }
}
