package com.sicau.enums;

import lombok.Getter;

/**
 * Created by wzw on 2019/2/18
 *
 * @Author wzw
 */
@Getter
public enum ExamineTypeEnum {
    WAIT(0, "未审核"),
    SUCCESS(1, "已审核"),
    FAIL(2, "审核未通过")
    ;

    private Integer code;

    private String msg;

    ExamineTypeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
