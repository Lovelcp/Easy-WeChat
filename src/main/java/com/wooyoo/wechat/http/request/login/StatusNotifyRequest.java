package com.wooyoo.wechat.http.request.login;

import com.wooyoo.wechat.http.request.common.BaseRequest;
import com.wooyoo.wechat.util.WeChatUtil;

public class StatusNotifyRequest {
    private BaseRequest BaseRequest;
    private String ClientMsgId;
    private int Code;
    private String FromUserName;
    private String ToUserName;

    public StatusNotifyRequest(BaseRequest baseRequest, String selfUserName) {
        BaseRequest = baseRequest;
        ClientMsgId = WeChatUtil.generateClientMsgId();
        // TODO move to constants
        Code = 3;
        FromUserName = selfUserName;
        ToUserName = selfUserName;
    }

    public BaseRequest getBaseRequest() {
        return BaseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        BaseRequest = baseRequest;
    }

    public String getClientMsgId() {
        return ClientMsgId;
    }

    public void setClientMsgId(String clientMsgId) {
        ClientMsgId = clientMsgId;
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getFromUserName() {
        return FromUserName;
    }

    public void setFromUserName(String fromUserName) {
        FromUserName = fromUserName;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
}
