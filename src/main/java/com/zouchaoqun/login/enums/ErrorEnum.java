package com.zouchaoqun.login.enums;

/**
 * Created by zouchaoqun on 2020/9/13.
 */
public enum ErrorEnum {

    SUCCESS(10000, "成功", ""),
    DATASOURCE_ERROR(20000, "数据库密码解密失败", ""),
    DATASOURCE_DECRYPT_ERROR(20001, "数据库密码解密失败", ""),

    SYSTEME_ERROR(10001,"系统错误");



    private int errorCode;

    private String errorMsg;



    private String detailInfo;




    ErrorEnum(int errorCode, String errorMsg){
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }


    ErrorEnum(int errorCode, String errorMsg, String detailInfo){
        this.errorCode = errorCode;

        this.errorMsg = errorMsg;
        this.detailInfo = detailInfo;
    }


    public int getErrorCode() {

        return this.errorCode;
    }


    public String getErrorMsg(){
        return this.errorMsg;
    }

    public String getDetail() {
        return this.detailInfo;
    }

}
