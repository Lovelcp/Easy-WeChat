package com.wooyoo.wechat;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface WeChatHttpService {

    @GET("jslogin")
    Call<ResponseBody> getUUID(
            @Query("appid")
                    String appId,
            @Query("fun")
                    String fun);

    //https://login.wx.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN&_=1497538678103

    @GET("/qrcode/{uuid}")
    @Streaming
    Call<ResponseBody> downloadQRCode(
            @Path("uuid")
                    String uuid);

}
