package com.wooyoo.wechat.service;

import com.wooyoo.wechat.http.WeChatContext;
import com.wooyoo.wechat.http.api.WeChatMessageApi;
import com.wooyoo.wechat.http.request.message.SendTextMsgRequest;
import com.wooyoo.wechat.http.response.message.SendTextMsgResponse;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class WeChatMessageService {
    protected ApiFactory apiFactory;
    protected WeChatContext weChatContext;
    protected WeChatMessageApi weChatMessageApi;

    public WeChatMessageService(WeChatContext weChatContext, ApiFactory apiFactory) {
        this.weChatContext = weChatContext;
        this.apiFactory = apiFactory;
        apiFactory.setBaseUrl(weChatContext.getWeChatHosts()
                                           .getDefaultHost());
        this.weChatMessageApi = apiFactory.create(WeChatMessageApi.class);
    }

    /**
     * 发送消息
     *
     * @param toUserName
     * @param msg
     * @throws IOException
     */
    public void sendTextMessage(String toUserName, String msg) throws IOException {
        Call<SendTextMsgResponse> responseCall = weChatMessageApi.sendTextMessage(new SendTextMsgRequest(weChatContext.getBaseRequest(), weChatContext.getSelf()
                                                                                                                                                      .getUserName(),
                toUserName, msg));
        Response<SendTextMsgResponse> response = responseCall.execute();
        SendTextMsgResponse responseBody = response.body();
    }
}
