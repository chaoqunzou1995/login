package com.zouchaoqun.login.exception;

import com.zouchaoqun.login.enums.ErrorEnum;

/**
 * Created by zouchaoqun on 2020/9/13.
 */
public class BizException extends RuntimeException {

    private final ErrorEnum errorEnum;

    private int errorCode;

    private String errorMSg;


    public BizException(ErrorEnum errorEnum, Throwable e) {
        super(errorEnum.getErrorMsg());
        this.errorEnum = errorEnum;

    }




}
