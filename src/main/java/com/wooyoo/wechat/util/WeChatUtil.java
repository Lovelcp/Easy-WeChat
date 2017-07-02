package com.wooyoo.wechat.util;

import com.wooyoo.wechat.http.WeChatContext;

public final class WeChatUtil {
    private WeChatUtil() {
    }

    /**
     * 通过阅读web微信的js代码，我们可以得知DeviceID为e加上随机的15位数字
     * 详见截图：http://7xrd8h.com1.z0.glb.clouddn.com/2017-06-25-093627.jpg
     *
     * @return
     */
    public static String generateDeviceID() {
        return "e" + ("" + Math.random()).substring(2, 17);
    }

    /**
     * 生成该微信微信接下来访问的各个hosts
     *
     * @param defaultHost
     * @return
     */
    public static WeChatContext.WeChatHosts generateWeChatHosts(String defaultHost) {
        String loginHost, fileHost, webpushHost;

        if (defaultHost.contains("wx2.qq.com")) {
            loginHost = "https://login.wx2.qq.com";
            fileHost = "https://file.wx2.qq.com";
            webpushHost = "https://webpush.wx2.qq.com";
        }
        else if (defaultHost.contains("wx8.qq.com")) {
            loginHost = "https://login.wx8.qq.com";
            fileHost = "https://file.wx8.qq.com";
            webpushHost = "https://webpush.wx8.qq.com";
        }
        else if (defaultHost.contains("qq.com")) {
            loginHost = "https://login.wx.qq.com";
            fileHost = "https://file.wx.qq.com";
            webpushHost = "https://webpush.wx.qq.com";
        }
        else if (defaultHost.contains("web2.wechat.com")) {
            loginHost = "https://login.web2.wechat.com";
            fileHost = "https://file.web2.wechat.com";
            webpushHost = "https://webpush.web2.wechat.com";
        }
        else if (defaultHost.contains("wechat.com")) {
            loginHost = "https://login.web.wechat.com";
            fileHost = "https://file.web.wechat.com";
            webpushHost = "https://webpush.web.wechat.com";
        }
        else {
            loginHost = "https://login.wx.qq.com";
            fileHost = "https://file.wx.qq.com";
            webpushHost = "https://webpush.wx.qq.com";
        }

        WeChatContext.WeChatHosts weChatHosts = new WeChatContext.WeChatHosts();
        weChatHosts.setDefaultHost(defaultHost);
        weChatHosts.setLoginHost(loginHost);
        weChatHosts.setFileHost(fileHost);
        weChatHosts.setWebpushHost(webpushHost);
        return weChatHosts;
    }
}
