package com.wooyoo.wechat.http.api;

import com.wooyoo.wechat.http.request.message.RevokeMsgRequest;
import com.wooyoo.wechat.http.request.message.SendTextMsgRequest;
import com.wooyoo.wechat.http.response.message.RevokeMsgResponse;
import com.wooyoo.wechat.http.response.message.SendTextMsgResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * 消息相关的接口
 */
public interface WeChatMessageApi {

    /**
     * 发送文本消息
     *
     * @param sendTextMsgRequest
     * @return
     */
    @POST("cgi-bin/mmwebwx-bin/webwxsendmsg")
    Call<SendTextMsgResponse> sendTextMessage(
            @Body
                    SendTextMsgRequest sendTextMsgRequest);

    /**
     * 撤回消息
     *
     * @param revokeMsgRequest
     * @return
     */
    @POST("cgi-bin/mmwebwx-bin/webwxsendmsg")
    Call<RevokeMsgResponse> revokeMessage(
            @Body
                    RevokeMsgRequest revokeMsgRequest);
}
