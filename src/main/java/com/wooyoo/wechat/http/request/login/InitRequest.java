package com.wooyoo.wechat.http.request.login;

import com.wooyoo.wechat.http.request.common.BaseRequest;
import com.wooyoo.wechat.util.WeChatUtil;

public class InitRequest {
    private BaseRequest BaseRequest;

    public InitRequest(Long Uin, String Sid, String Skey) {
        this(Uin, Sid, Skey, WeChatUtil.generateDeviceID());
    }

    public InitRequest(Long Uin, String Sid, String Skey, String DeviceID) {
        this.BaseRequest = new BaseRequest(Uin, Sid, Skey, DeviceID);
    }

    public BaseRequest getBaseRequest() {
        return BaseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        BaseRequest = baseRequest;
    }
}
