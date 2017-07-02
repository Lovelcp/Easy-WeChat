package com.wooyoo.wechat.http.request.common;

public class BaseRequest {
    private Long Uin;
    private String Sid;
    private String Skey;
    private String DeviceID;

    public BaseRequest(Long uin, String sid, String skey, String deviceID) {
        Uin = uin;
        Sid = sid;
        Skey = skey;
        DeviceID = deviceID;
    }

    public Long getUin() {
        return Uin;
    }

    public void setUin(Long uin) {
        Uin = uin;
    }

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSkey() {
        return Skey;
    }

    public void setSkey(String skey) {
        Skey = skey;
    }

    public String getDeviceID() {
        return DeviceID;
    }

    public void setDeviceID(String deviceID) {
        DeviceID = deviceID;
    }
}