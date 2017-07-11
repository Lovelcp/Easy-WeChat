package com.wooyoo.wechat.http.response.login;

import com.wooyoo.wechat.http.response.common.BaseResponse;

public class StatusNotifyResponse {
    private BaseResponse BaseResponse;
    private String MsgID;

    public BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public String getMsgID() {
        return MsgID;
    }

    public void setMsgID(String msgID) {
        MsgID = msgID;
    }
}
