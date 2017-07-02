package com.wooyoo.wechat.http.api;

import com.wooyoo.wechat.http.response.contact.GetContactResponse;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeChatContactApi {
    /**
     * 获取微信联系人
     *
     * @param passTicket
     * @param skey
     * @return
     */
    @POST("cgi-bin/mmwebwx-bin/webwxgetcontact")
    Call<GetContactResponse> getContacts(
            @Query("pass_ticket")
                    String passTicket,
            @Query("skey")
                    String skey);
}
