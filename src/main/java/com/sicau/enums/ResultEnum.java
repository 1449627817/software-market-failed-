package com.sicau.enums;


/**
 * http响应数据状态code
 * @author BeFondOfTaro
 * Created in 12:43 2018/1/18
 */
public enum ResultEnum {

    UNKNOWN_ERROR(-1, "未知错误"),
    SUCCESS(0,"成功"),
    PARAM_ERROR(1, "参数不正确"),
    LOGIN_FAILED(2, "登录失败"),
    USER_NOT_FOUND(3, "用户不存在"),
    PASSWORD_INCORRECT(4, "用户名或密码不正确"),
    INVALID_TOKEN(5, "无效的token"),
    UNAUTHORIZED(6, "没有权限"),
    DUPLICATE_USERNAME(7, "用户名重复"),
    DELETE_FAILED(8, "删除失败"),
    DATABASE_ERROR(9,"数据库错误"),
    RESOURCE_NOT_FOUND(10, "资源不存在"),
    USER_EXIST(11,"用户已存在"),

    ALTER_ORDERSTAGE_REPEAT(52,"错误：在同一状态重复提交");

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }}
