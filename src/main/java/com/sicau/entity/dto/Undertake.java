package com.sicau.entity.dto;

public class Undertake {
    private int undertakeId;

    private String teamId;

    private String projectId;

    private String state;

    private String functionList;

    private String timeNode;

    private String time;

    public  Undertake(){}

    public Undertake(String teamId, String projectId, String state, String functionList, String timeNode) {
        this.teamId = teamId;
        this.projectId = projectId;
        this.state = state;
        this.functionList = functionList;
        this.timeNode = timeNode;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId == null ? null : teamId.trim();
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId == null ? null : projectId.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getFunctionList() {
        return functionList;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getTime() {
        return time;
    }

    public void setFunctionList(String functionList) {
        this.functionList = functionList;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getUndertakeId() {
        return undertakeId;
    }

    public void setUndertakeId(int undertakeId) {
        this.undertakeId = undertakeId;
    }

    @Override
    public String toString() {
        return "Undertake{" +
                "undertakeId='" + undertakeId + '\'' +
                ", teamId='" + teamId + '\'' +
                ", projectId='" + projectId + '\'' +
                ", state='" + state + '\'' +
                ", functionList='" + functionList + '\'' +
                ", timeNode='" + timeNode + '\'' +
                '}';
    }
}