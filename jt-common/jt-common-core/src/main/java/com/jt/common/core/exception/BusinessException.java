package com.jt.common.core.exception;

import com.jt.common.core.enums.ErrorEnum;
import lombok.Getter;


/**
 * BusinessException
 * 自定义业务异常
 *
 * @Author: jt
 * @Date: 2022/8/16 16:38
 */
@Getter
public class BusinessException extends RuntimeException{
    protected final ErrorEnum errorEnum;

    /**
     * 填充动态数据，不如想定位抛出异常的具体某一条数据
     * @param errorEnum
     */
    //这个地方为什么定义的时候是Object[]，但是拿回的时候入参是任意
    protected Object[] dynamicData;

    public BusinessException(ErrorEnum errorEnum) {

        this.errorEnum = errorEnum;
    }

    public BusinessException(ErrorEnum errorEnum, Object... dynamicData) {

        this.errorEnum = errorEnum;
        this.dynamicData = dynamicData;
    }
}
