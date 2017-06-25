package com.wooyoo.wechat.http.request;

public class InitRequest {
    private BaseRequest BaseRequest;

    public InitRequest(Long Uin, String Sid, String Skey) {
        // 通过阅读web微信的js代码，我们可以得知DeviceID为e加上随机的15位数字
        // 详见截图：http://7xrd8h.com1.z0.glb.clouddn.com/2017-06-25-093627.jpg
        this(Uin, Sid, Skey, "e" + ("" + Math.random()).substring(2, 17));
    }

    public InitRequest(Long Uin, String Sid, String Skey, String DeviceID) {
        this.BaseRequest = new BaseRequest(Uin, Sid, Skey, DeviceID);
    }

    public InitRequest.BaseRequest getBaseRequest() {
        return BaseRequest;
    }

    public void setBaseRequest(InitRequest.BaseRequest baseRequest) {
        BaseRequest = baseRequest;
    }

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
}
