package com.wooyoo.wechat.http.api;

import com.wooyoo.wechat.http.request.InitRequest;
import com.wooyoo.wechat.http.response.InitResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 登录相关的接口
 */
public interface WeChatLoginApi {

    /**
     * 获取UUID
     *
     * @param appId
     * @param fun
     * @return
     */
    @GET("jslogin")
    Call<ResponseBody> getUUID(
            @Query("appid")
                    String appId,
            @Query("fun")
                    String fun);

    //https://login.wx.qq.com/jslogin?appid=wx782c26e4c19acffb&redirect_uri=https%3A%2F%2Fwx.qq.com%2Fcgi-bin%2Fmmwebwx-bin%2Fwebwxnewloginpage&fun=new&lang=zh_CN&_=1497538678103

    /**
     * 获取登录二维码
     *
     * @param uuid
     * @return
     */
    @GET("/qrcode/{uuid}")
    @Streaming
    Call<ResponseBody> showQRCode(
            @Path("uuid")
                    String uuid);

    /**
     * 轮询接口，判断用户是否已经扫描二维码以及是否在手机上确认登录
     *
     * @param loginicon
     * @param uuid
     * @param tip       0：等待微信客户端确认 1：等待扫描二维码
     * @return
     */
    @GET("cgi-bin/mmwebwx-bin/login")
    Call<ResponseBody> waitForLogin(
            @Query("loginicon")
                    boolean loginicon,
            @Query("uuid")
                    String uuid,
            @Query("tip")
                    int tip);

    /**
     * 底层登录接口，用于获取cookie等信息
     *
     * @param loginUrl
     * @return
     */
    @GET
    Call<ResponseBody> login(
            @Url
                    String loginUrl);

    /**
     * 微信初始化
     *
     * @param passTicket
     * @param skey
     * @param initRequest
     * @return
     */
    @POST("cgi-bin/mmwebwx-bin/webwxinit")
    Call<InitResponse> init(
            @Query("pass_ticket")
                    String passTicket,
            @Query("skey")
                    String skey,
            @Body
                    InitRequest initRequest);
}
