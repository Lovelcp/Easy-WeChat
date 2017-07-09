package com.wooyoo.wechat.http.request.message;

import com.wooyoo.wechat.http.request.common.BaseRequest;
import com.wooyoo.wechat.util.WeChatUtil;

public class SendTextMsgRequest {
    private BaseRequest BaseRequest;
    private Msg Msg;
    private Integer Scene;

    public SendTextMsgRequest(BaseRequest baseRequest, String fromUserName, String toUserName, String msg) {
        this.BaseRequest = baseRequest;
        this.Msg = new Msg(msg, fromUserName, toUserName, WeChatUtil.generateClientMsgId());
        // TODO 默认为0
        this.Scene = 0;
    }

    public BaseRequest getBaseRequest() {
        return BaseRequest;
    }

    public void setBaseRequest(BaseRequest baseRequest) {
        BaseRequest = baseRequest;
    }

    public SendTextMsgRequest.Msg getMsg() {
        return Msg;
    }

    public void setMsg(SendTextMsgRequest.Msg msg) {
        Msg = msg;
    }

    public Integer getScene() {
        return Scene;
    }

    public void setScene(Integer scene) {
        Scene = scene;
    }

    public static class Msg {
        private Integer Type;
        private String Content;
        private String FromUserName;
        private String ToUserName;
        private String LocalID;
        private String ClientMsgId;

        public Msg(String content, String fromUserName, String toUserName, String clientMsgId) {
            Content = content;
            FromUserName = fromUserName;
            ToUserName = toUserName;
            ClientMsgId = clientMsgId;

            LocalID = ClientMsgId;

            // TODO 文字消息默认是1
            Type = 1;
        }

        public Integer getType() {
            return Type;
        }

        public void setType(Integer type) {
            Type = type;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String content) {
            Content = content;
        }

        public String getFromUserName() {
            return FromUserName;
        }

        public void setFromUserName(String fromUserName) {
            FromUserName = fromUserName;
        }

        public String getToUserName() {
            return ToUserName;
        }

        public void setToUserName(String toUserName) {
            ToUserName = toUserName;
        }

        public String getLocalID() {
            return LocalID;
        }

        public void setLocalID(String localID) {
            LocalID = localID;
        }

        public String getClientMsgId() {
            return ClientMsgId;
        }

        public void setClientMsgId(String clientMsgId) {
            ClientMsgId = clientMsgId;
        }
    }

}
