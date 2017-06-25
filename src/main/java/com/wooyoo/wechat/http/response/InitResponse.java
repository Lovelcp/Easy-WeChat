package com.wooyoo.wechat.http.response;

import com.wooyoo.wechat.http.common.Contact;
import com.wooyoo.wechat.http.common.SyncKey;
import com.wooyoo.wechat.http.common.User;

import java.util.List;

public class InitResponse {
    private BaseResponse BaseResponse;
    private String ChatSet;
    private Long ClickReportInterval;
    private Long ClientVersion;
    private List<Contact> ContactList;
    private Long Count;
    private int GrayScale;
    private int InviteStartCount;
    private int MPSubscribeMsgCount;

    // TODO
    private List<?> MPSubscribeMsgList;

    private String SKey;
    private SyncKey SyncKey;
    private Long SystemTime;
    private User User;

    public InitResponse.BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(InitResponse.BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public String getChatSet() {
        return ChatSet;
    }

    public void setChatSet(String chatSet) {
        ChatSet = chatSet;
    }

    public Long getClickReportInterval() {
        return ClickReportInterval;
    }

    public void setClickReportInterval(Long clickReportInterval) {
        ClickReportInterval = clickReportInterval;
    }

    public Long getClientVersion() {
        return ClientVersion;
    }

    public void setClientVersion(Long clientVersion) {
        ClientVersion = clientVersion;
    }

    public List<Contact> getContactList() {
        return ContactList;
    }

    public void setContactList(List<Contact> contactList) {
        ContactList = contactList;
    }

    public Long getCount() {
        return Count;
    }

    public void setCount(Long count) {
        Count = count;
    }

    public int getGrayScale() {
        return GrayScale;
    }

    public void setGrayScale(int grayScale) {
        GrayScale = grayScale;
    }

    public int getInviteStartCount() {
        return InviteStartCount;
    }

    public void setInviteStartCount(int inviteStartCount) {
        InviteStartCount = inviteStartCount;
    }

    public int getMPSubscribeMsgCount() {
        return MPSubscribeMsgCount;
    }

    public void setMPSubscribeMsgCount(int MPSubscribeMsgCount) {
        this.MPSubscribeMsgCount = MPSubscribeMsgCount;
    }

    public List<?> getMPSubscribeMsgList() {
        return MPSubscribeMsgList;
    }

    public void setMPSubscribeMsgList(List<?> MPSubscribeMsgList) {
        this.MPSubscribeMsgList = MPSubscribeMsgList;
    }

    public String getSKey() {
        return SKey;
    }

    public void setSKey(String SKey) {
        this.SKey = SKey;
    }

    public com.wooyoo.wechat.http.common.SyncKey getSyncKey() {
        return SyncKey;
    }

    public void setSyncKey(com.wooyoo.wechat.http.common.SyncKey syncKey) {
        SyncKey = syncKey;
    }

    public Long getSystemTime() {
        return SystemTime;
    }

    public void setSystemTime(Long systemTime) {
        SystemTime = systemTime;
    }

    public com.wooyoo.wechat.http.common.User getUser() {
        return User;
    }

    public void setUser(com.wooyoo.wechat.http.common.User user) {
        User = user;
    }

    public class BaseResponse {
        private String ErrMsg;
        private int Ret;

        public String getErrMsg() {
            return ErrMsg;
        }

        public void setErrMsg(String errMsg) {
            ErrMsg = errMsg;
        }

        public int getRet() {
            return Ret;
        }

        public void setRet(int ret) {
            Ret = ret;
        }
    }
}
