package com.wooyoo.wechat.http.api;

import com.wooyoo.wechat.http.request.message.SendTextMsgRequest;
import com.wooyoo.wechat.http.response.message.SendTextMsgResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface WeChatMessageApi {

    @POST("cgi-bin/mmwebwx-bin/webwxsendmsg")
    Call<SendTextMsgResponse> sendTextMessage(
            @Body
                    SendTextMsgRequest sendTextMsgRequest);
}
