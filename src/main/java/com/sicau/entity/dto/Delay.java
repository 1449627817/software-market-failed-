package com.sicau.entity.dto;

public class Delay {
    private String delayId;

    private String delayTime;

    private String state;

    public Delay(){
        super();
    }

    public Delay(String delayId, String delayTime, String state){
        super();
        this.delayId=delayId;
        this.delayTime=delayTime;
        this.state=state;
    }

    public String getDelayId() {
        return delayId;
    }

    public void setDelayId(String delayId) {
        this.delayId = delayId ;
    }

    public String getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(String delayTime) {
        this.delayTime = delayTime ;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state ;
    }

    @Override
    public String toString() {
        return "Delay{" +
                "delayId='" + delayId + '\'' +
                ", delayTime='" + delayTime + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}