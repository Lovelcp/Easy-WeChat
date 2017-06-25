package com.wooyoo.wechat.http;

public class WeChatSession {
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

    @Override
    public String toString() {
        return "WeChatSession{" + "skey='" + skey + '\'' + ", sid='" + sid + '\'' + ", uin='" + uin + '\'' + ", passTicket='" + passTicket + '\'' + '}';
    }
}
