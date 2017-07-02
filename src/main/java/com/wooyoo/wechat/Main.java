package com.wooyoo.wechat;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        //@see http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0/
        System.setProperty("jsse.enableSNIExtension", "false");

        WeChatClient weChatClient = new WeChatClient();
        String uuid = weChatClient.getUUID();
        weChatClient.showQRCode(uuid);
        weChatClient.waitForLogin();
        weChatClient.init();
        weChatClient.getContacts();
    }

}
