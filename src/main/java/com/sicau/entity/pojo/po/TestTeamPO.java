package com.sicau.entity.pojo.po;


import java.util.List;
import java.util.Map;

/**
 * @author ttxxi write on 2019-02-10
 * 综合了team teamAndUser user表的实体类
 */
public class TestTeamPO {
    private String teamId;
    private String teamName;
    private String direction;
    private Map<String, String> captainInformation;
    private List<Map<String, String>> memberInformation;
    private String description;
    private String number;
    private String teamType;
    private String teamState;

    private String createTime;

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Map<String, String> getCaptainInformation() {
        return captainInformation;
    }

    public void setCaptainInformation(Map<String, String> captainInformation) {
        this.captainInformation = captainInformation;
    }

    public List<Map<String, String>> getMemberInformation() {
        return memberInformation;
    }

    public void setMemberInformation(List<Map<String, String>> memberInformation) {
        this.memberInformation = memberInformation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getTeamType() {
        return teamType;
    }

    public void setTeamType(String teamType) {
        this.teamType = teamType;
    }

    public String getTeamState() {
        return teamState;
    }

    public void setTeamState(String teamState) {
        this.teamState = teamState;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "TeamPO{" +
                "teamId='" + teamId + '\'' +
                ", teamName='" + teamName + '\'' +
                ", direction='" + direction + '\'' +
                ", captainInformation='" + captainInformation + '\'' +
                ", memberInformation='" + memberInformation + '\'' +
                ", description='" + description + '\'' +
                ", number='" + number + '\'' +
                ", teamType='" + teamType + '\'' +
                ", teamState='" + teamState + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
