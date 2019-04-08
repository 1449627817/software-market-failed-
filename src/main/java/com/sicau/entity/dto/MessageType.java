package com.sicau.entity.dto;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 0:40 2019/2/18
 **/
public class MessageType {

    private String id;
    private String name;

    public MessageType(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public MessageType() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
