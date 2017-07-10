package com.wooyoo.wechat;

import com.wooyoo.wechat.constant.URLs;
import com.wooyoo.wechat.http.WeChatContext;
import com.wooyoo.wechat.repo.WeChatContacts;
import com.wooyoo.wechat.service.ApiFactory;
import com.wooyoo.wechat.service.WeChatContactService;
import com.wooyoo.wechat.service.WeChatLoginService;
import com.wooyoo.wechat.service.WeChatMessageService;

import java.io.IOException;

public class WeChatClient {

    /**
     * services
     */
    protected WeChatLoginService weChatLoginService;
    protected WeChatContactService weChatContactService;
    protected WeChatMessageService weChatMessageService;

    /**
     * wechat meta data
     */
    protected WeChatContext weChatContext;
    protected WeChatContacts weChatContacts;

    protected ApiFactory apiFactory;

    public WeChatClient() {
        this.apiFactory = new ApiFactory(URLs.DEFAULT_BASE_LOGIN_URL);
        this.weChatLoginService = new WeChatLoginService(apiFactory);
    }

    public WeChatContext getWeChatContext() {
        return weChatContext;
    }

    /**
     * 获取UUID
     *
     * @return
     * @throws IOException
     */
    public String getUUID() throws IOException {
        return weChatLoginService.getUUID();
    }

    /**
     * 展示QRCode
     *
     * @param uuid
     */
    public void showQRCode(String uuid) {
        weChatLoginService.showQRCode(uuid);
    }

    /**
     * 等待登录
     *
     * @throws IOException
     */
    public void waitForLogin() throws IOException {
        this.weChatContext = weChatLoginService.waitForLogin();

        // services initialization
        // TODO
        this.weChatContactService = new WeChatContactService(weChatContext, apiFactory);
        this.weChatMessageService = new WeChatMessageService(weChatContext, apiFactory);
    }

    /**
     * 微信初始化
     *
     * @throws IOException
     */
    public void init() throws IOException {
        weChatLoginService.init();
    }

    /**
     * 加载联系人
     *
     * @throws IOException
     */
    public WeChatContacts getContacts() throws IOException {
        this.weChatContacts = weChatContactService.getContacts();
        return weChatContacts;
    }

    /**
     * 发送消息
     *
     * @param toUserName
     * @param msg
     * @throws IOException
     */
    public void sendTextMessage(String toUserName, String msg) throws IOException {
        weChatMessageService.sendTextMessage(toUserName, msg);
    }

    /**
     * 撤回消息
     *
     * @param msgId
     * @param toUserName
     * @throws IOException
     */
    public void revokeMessage(String msgId, String toUserName) throws IOException {
        weChatMessageService.revokeMessage(msgId, toUserName);
    }
}
