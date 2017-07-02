package com.wooyoo.wechat.http;

public class WeChatContext {
    private WeChatHosts weChatHosts;

    private String skey;
    private String sid;
    private Long uin;
    private String passTicket;

    public String getSkey() {
        return skey;
    }

    public void setSkey(String skey) {
        this.skey = skey;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public Long getUin() {
        return uin;
    }

    public void setUin(Long uin) {
        this.uin = uin;
    }

    public String getPassTicket() {
        return passTicket;
    }

    public void setPassTicket(String passTicket) {
        this.passTicket = passTicket;
    }

    public WeChatHosts getWeChatHosts() {
        return weChatHosts;
    }

    public void setWeChatHosts(WeChatHosts weChatHosts) {
        this.weChatHosts = weChatHosts;
    }

    public static class WeChatHosts {
        private String defaultHost;
        private String loginHost;
        private String fileHost;
        private String webpushHost;

        public String getDefaultHost() {
            return defaultHost;
        }

        public void setDefaultHost(String defaultHost) {
            this.defaultHost = defaultHost;
        }

        public String getLoginHost() {
            return loginHost;
        }

        public void setLoginHost(String loginHost) {
            this.loginHost = loginHost;
        }

        public String getFileHost() {
            return fileHost;
        }

        public void setFileHost(String fileHost) {
            this.fileHost = fileHost;
        }

        public String getWebpushHost() {
            return webpushHost;
        }

        public void setWebpushHost(String webpushHost) {
            this.webpushHost = webpushHost;
        }

        @Override
        public String toString() {
            return "WeChatHosts{" + "defaultHost='" + defaultHost + '\'' + ", loginHost='" + loginHost + '\'' + ", fileHost='" + fileHost + '\''
                    + ", webpushHost='" + webpushHost + '\'' + '}';
        }
    }

    @Override
    public String toString() {
        return "WeChatContext{" + "wxHost=" + weChatHosts + ", skey='" + skey + '\'' + ", sid='" + sid + '\'' + ", uin=" + uin + ", passTicket='" + passTicket
                + '\'' + '}';
    }
}
