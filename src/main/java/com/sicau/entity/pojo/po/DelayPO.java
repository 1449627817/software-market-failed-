package com.sicau.entity.pojo.po;

import com.sicau.entity.dto.Delay;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 20:16 2019/3/24
 **/
public class DelayPO extends Delay {

    private String teamId;

    public DelayPO() {
    }

    public DelayPO(String delayId, String delayTime, String state, String teamId) {
        super(delayId, delayTime, state);
        this.teamId = teamId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }
}
