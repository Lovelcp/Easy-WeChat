package com.wooyoo.wechat;

import com.wooyoo.wechat.http.WeChatSession;
import com.wooyoo.wechat.service.WeChatLoginService;

import java.io.IOException;

public class WeChatClient {

    protected WeChatLoginService weChatLoginService;
    protected WeChatSession weChatSession;

    public WeChatClient() {
        this.weChatLoginService = new WeChatLoginService();
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
        this.weChatSession = weChatLoginService.waitForLogin();
    }

    /**
     * 微信初始化
     *
     * @throws IOException
     */
    public void init() throws IOException {
        weChatLoginService.init();
    }

}
