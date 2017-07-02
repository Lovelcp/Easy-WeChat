package com.wooyoo.wechat.http.response.common;

public class BaseResponse {
    private String ErrMsg;
    private int Ret;

    public String getErrMsg() {
        return ErrMsg;
    }

    public void setErrMsg(String errMsg) {
        ErrMsg = errMsg;
    }

    public int getRet() {
        return Ret;
    }

    public void setRet(int ret) {
        Ret = ret;
    }
}