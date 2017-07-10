package com.wooyoo.wechat.http.response.message;

import com.wooyoo.wechat.http.response.common.BaseResponse;

public class RevokeMsgResponse {
    private BaseResponse BaseResponse;
    private String Introduction;
    private String SysWording;

    public BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }

    public String getSysWording() {
        return SysWording;
    }

    public void setSysWording(String sysWording) {
        SysWording = sysWording;
    }
}
