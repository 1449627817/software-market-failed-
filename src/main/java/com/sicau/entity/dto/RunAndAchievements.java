package com.sicau.entity.dto;

/**
 * Description:runAndAchievement实体类
 * @author tzw
 * CreateTime 18:20 2019/2/10
 **/
public class RunAndAchievements {

    private int id;

    private String runId;

    private String achievementsId;

    public RunAndAchievements(int id, String runId, String achievementsId) {
        this.id = id;
        this.runId = runId;
        this.achievementsId = achievementsId;
    }

    public RunAndAchievements(){}

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getAchievementsId() {
        return achievementsId;
    }

    public void setAchievementsId(String achievementsId) {
        this.achievementsId = achievementsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
