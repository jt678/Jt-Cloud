package com.jt.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * ErrorEnum
 *
 * @Author: jt
 * @Date: 2022/8/16 16:40
 */
@AllArgsConstructor
@Getter
public enum ErrorEnum {

    LOGIN_USERNAME_OR_PSW_NULL(400001,"账号/密码不能为空！","这里以后做国际化"),

    LOGIN_USERNAME_FORMAT_ERROR(400002,"账号格式错误！","这里以后做国际化");
    private int id;
    private String error;
    //后面做国际化留的占位
    private String remark;
}
