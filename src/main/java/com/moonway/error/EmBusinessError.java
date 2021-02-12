package com.moonway.error;

public enum EmBusinessError implements CommonError {

    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知錯誤"),
    USER_NOT_EXISTS(20001,"用户不存在"),
    USER_LOGIN_NOT_MATCH(20002,"用户账号或密码错误")

    ;

    private int errCode;
    private String errMsg;

    private EmBusinessError(int errCode,String errMsg){
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrCode() {
        return errCode;
    }

    @Override
    public String getErrMsg() {
        return errMsg;
                
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}
