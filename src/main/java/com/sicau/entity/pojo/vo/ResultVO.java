package com.sicau.entity.pojo.vo;
/**
 * 返回视图
 * @author wzw
 * Created in 12:43 2019/1/25
 */
public class ResultVO {
    private Integer status;

    private String msg;

    private Object data;

    public ResultVO() {

    }

    public ResultVO(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
