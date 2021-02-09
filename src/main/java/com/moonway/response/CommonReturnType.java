package com.moonway.response;

import java.util.HashMap;

public class CommonReturnType {
    private String status;
    private Object data;


    public static CommonReturnType create(Object result){
        return CommonReturnType.create(result, STATUS.SUCCESSFUL);
    }

    public static CommonReturnType create(Object result,STATUS status){
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status.getStr_status());
        type.setData(result);
        return type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public enum STATUS {
        SUCCESSFUL("success"),
        FAIL("fail");

        private String str_status;
        STATUS(String str_status){
            this.str_status = str_status;
        }

        public String getStr_status() {
            return str_status;
        }
    }
}

