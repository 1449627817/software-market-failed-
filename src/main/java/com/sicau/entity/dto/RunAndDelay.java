package com.sicau.entity.dto;

/**
 * Description:
 *
 * @author tzw
 * CreateTime 0:45 2019/2/18
 **/
public class RunAndDelay {

    private int id;
    private String runId;
    private String delayId;

    public RunAndDelay(int id, String runId, String delayId) {
        this.id = id;
        this.runId = runId;
        this.delayId = delayId;
    }

    public RunAndDelay() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRunId() {
        return runId;
    }

    public void setRunId(String runId) {
        this.runId = runId;
    }

    public String getDelayId() {
        return delayId;
    }

    public void setDelayId(String delayId) {
        this.delayId = delayId;
    }
}
