package com.sicau.enums;


/**
 * 用户登录认证类型
 * @author BeFondOfTaro
 * Created at 18:22 2018/1/19
 */
public enum IdentifyTypeEnum {

    USERNAME(0, "用户名密码登录"),
    WECHAT(1, "微信登录"),
    PHONE(2, "手机登录"),
    EMAIL(3, "邮箱登录")
    ;

    private Integer code;

    private String typeName;

    IdentifyTypeEnum(int code, String typeName) {
        this.code = code;
        this.typeName = typeName;
    }

    public Integer getCode() {
        return code;
    }
}
