package com.sicau.entity.dto;

import org.springframework.stereotype.Component;

/**
 * Description:成果实体类
 * @author tzw
 * CreateTime 23:18 2019/2/9
 **/

@Component
public class Achievements {

    private String achievementId;

    private String timeNode;

    private String achievementContent;

    private String state;

    private String commitTime;

    public Achievements(){}

    public Achievements(String achievementId, String timeNode, String achievementContent, String state, String commitTime) {
        this.achievementId = achievementId;
        this.timeNode = timeNode;
        this.achievementContent = achievementContent;
        this.state = state;
        this.commitTime = commitTime;
    }

    public String getAchievementId() {
        return achievementId;
    }

    public void setAchievementId(String achievementId) {
        this.achievementId = achievementId;
    }

    public String getTimeNode() {
        return timeNode;
    }

    public void setTimeNode(String timeNode) {
        this.timeNode = timeNode;
    }

    public String getAchievementContent() {
        return achievementContent;
    }

    public void setAchievementContent(String achievementContent) {
        this.achievementContent = achievementContent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }
}
