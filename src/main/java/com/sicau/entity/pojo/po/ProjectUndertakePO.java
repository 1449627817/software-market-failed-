package com.sicau.entity.pojo.po;

import java.util.List;

public class ProjectUndertakePO {

    private String projectId;

    private String projectTime;

    private String projectName;

    private String projectRequirement;

    private String projectDescribe;

    private String projectPrice;

    private String releaseTime;

    private String userId;

    private List<TeamUndertakePO> teamUndertakePOList;

    public ProjectUndertakePO(String projectId, String projectTime, String projectName, String projectRequirement, String projectDescribe, String projectPrice, String releaseTime, String userId) {
        this.projectId = projectId;
        this.projectTime = projectTime;
        this.projectName = projectName;
        this.projectRequirement = projectRequirement;
        this.projectDescribe = projectDescribe;
        this.projectPrice = projectPrice;
        this.releaseTime = releaseTime;
        this.userId = userId;
    }

    public ProjectUndertakePO(){}

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(String projectTime) {
        this.projectTime = projectTime;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectRequirement() {
        return projectRequirement;
    }

    public void setProjectRequirement(String projectRequirement) {
        this.projectRequirement = projectRequirement;
    }

    public String getProjectDescribe() {
        return projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }

    public String getProjectPrice() {
        return projectPrice;
    }

    public void setProjectPrice(String projectPrice) {
        this.projectPrice = projectPrice;
    }

    public String getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(String releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<TeamUndertakePO> getTeamUndertakePOList() {
        return teamUndertakePOList;
    }

    public void setTeamUndertakePOList(List<TeamUndertakePO> teamUndertakePOList) {
        this.teamUndertakePOList = teamUndertakePOList;
    }
}
