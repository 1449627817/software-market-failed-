package com.sicau.entity.dto;
/**
 * Description:成果类型实体类
 * @author tzw
 * CreateTime 13:36 2019/2/11
 **/
public class AchievementsType {

    private String id;

    private String name;

    public AchievementsType(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public AchievementsType(){}

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
