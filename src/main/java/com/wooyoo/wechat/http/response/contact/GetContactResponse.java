package com.wooyoo.wechat.http.response.contact;

import com.wooyoo.wechat.http.model.Contact;
import com.wooyoo.wechat.http.response.common.BaseResponse;

import java.util.List;

public class GetContactResponse {
    private BaseResponse BaseResponse;
    private Long MemberCount;
    private List<Contact> MemberList;
    private Long Seq;

    public com.wooyoo.wechat.http.response.common.BaseResponse getBaseResponse() {
        return BaseResponse;
    }

    public void setBaseResponse(com.wooyoo.wechat.http.response.common.BaseResponse baseResponse) {
        BaseResponse = baseResponse;
    }

    public Long getMemberCount() {
        return MemberCount;
    }

    public void setMemberCount(Long memberCount) {
        MemberCount = memberCount;
    }

    public List<Contact> getMemberList() {
        return MemberList;
    }

    public void setMemberList(List<Contact> memberList) {
        MemberList = memberList;
    }

    public Long getSeq() {
        return Seq;
    }

    public void setSeq(Long seq) {
        Seq = seq;
    }
}
