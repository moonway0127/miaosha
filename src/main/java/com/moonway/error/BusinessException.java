package com.moonway.error;

//包裝器業務異常類實現
public class BusinessException extends Exception implements CommonError{
    private CommonError commonError;

    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }


    public BusinessException(CommonError commonError, String errMsg){
        super();
        this.commonError = commonError;
        this.setErrorMsg(errMsg);
    }

    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrorMsg(String errMsg) {
        this.commonError.setErrorMsg(errMsg);
        return this;
    }


}
