package com.moonway.error;

public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrorMsg(String errMsg);

}
