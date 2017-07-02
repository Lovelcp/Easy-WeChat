package com.wooyoo.wechat.service;

import com.wooyoo.wechat.http.WeChatContext;
import com.wooyoo.wechat.http.api.WeChatContactApi;
import com.wooyoo.wechat.http.model.Contact;
import com.wooyoo.wechat.http.response.contact.GetContactResponse;
import com.wooyoo.wechat.repo.WeChatContacts;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

/**
 * 联系人相关接口
 */
public class WeChatContactService {
    protected WeChatContactApi weChatContactApi;
    protected WeChatContext weChatContext;
    protected ApiFactory apiFactory;
    protected String uuid;

    protected WeChatContacts weChatContacts; // 联系人

    public WeChatContactService(WeChatContext weChatContext, ApiFactory apiFactory) {
        this.weChatContext = weChatContext;
        this.apiFactory = apiFactory;
        apiFactory.setBaseUrl(weChatContext.getWeChatHosts()
                                           .getDefaultHost());
        this.weChatContactApi = apiFactory.create(WeChatContactApi.class);
    }

    /**
     * 获取联系人
     *
     * @return
     * @throws IOException
     */
    public WeChatContacts getContacts() throws IOException {
        Call<GetContactResponse> responseCall = weChatContactApi.getContacts(weChatContext.getPassTicket(), weChatContext.getSkey());
        Response<GetContactResponse> response = responseCall.execute();
        GetContactResponse responseBody = response.body();
        List<Contact> contacts = responseBody.getMemberList();
        contacts.forEach(contact -> System.out.println(contact.getUserName()));
        this.weChatContacts = new WeChatContacts(contacts);
        return new WeChatContacts(contacts);
    }
}
