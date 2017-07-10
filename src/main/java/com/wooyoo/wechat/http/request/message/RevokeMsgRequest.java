package com.wooyoo.wechat.http.request.message;

import com.wooyoo.wechat.http.request.common.BaseRequest;
import com.wooyoo.wechat.util.WeChatUtil;

public class RevokeMsgRequest {
    private BaseRequest BaseRequest;
    private String ClientMsgId;
    private String SvrMsgId;
    private String ToUserName;

    public RevokeMsgRequest(BaseRequest baseRequest, String svrMsgId, String toUserName) {
        BaseRequest = baseRequest;
        ClientMsgId = WeChatUtil.generateClientMsgId();
        SvrMsgId = svrMsgId;
        ToUserName = toUserName;
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

    public String getSvrMsgId() {
        return SvrMsgId;
    }

    public void setSvrMsgId(String svrMsgId) {
        SvrMsgId = svrMsgId;
    }

    public String getToUserName() {
        return ToUserName;
    }

    public void setToUserName(String toUserName) {
        ToUserName = toUserName;
    }
}
