package com.sicau.entity.dto;

/**
 * Description:成果多类型实体类
 * @author tzw
 * CreateTime 13:38 2019/2/11
 **/
public class AchievementsAndType {

    private int id;

    private String achievementsId;

    private String typeId;

    public AchievementsAndType(int id, String achievementsId, String typeId) {
        this.id = id;
        this.achievementsId = achievementsId;
        this.typeId = typeId;
    }
    public AchievementsAndType(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAchievementsId() {
        return achievementsId;
    }

    public void setAchievementsId(String achievementsId) {
        this.achievementsId = achievementsId;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
