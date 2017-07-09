package com.wooyoo.wechat.service;

import com.wooyoo.wechat.constant.WeChatConstants;
import com.wooyoo.wechat.exception.WeChatException;
import com.wooyoo.wechat.http.WeChatContext;
import com.wooyoo.wechat.http.api.WeChatLoginApi;
import com.wooyoo.wechat.http.request.login.InitRequest;
import com.wooyoo.wechat.http.response.login.InitResponse;
import com.wooyoo.wechat.util.HttpUtil;
import com.wooyoo.wechat.util.QRCodeUtil;
import com.wooyoo.wechat.util.RegexUtil;
import com.wooyoo.wechat.util.WeChatUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.IOException;
import java.util.Objects;

/**
 * 登录相关的接口
 */
public class WeChatLoginService {

    protected WeChatLoginApi weChatLoginApi;
    protected WeChatContext weChatContext;
    protected ApiFactory apiFactory;
    protected String uuid;

    public WeChatLoginService(ApiFactory apiFactory) {
        this.apiFactory = apiFactory;
        this.weChatLoginApi = apiFactory.create(WeChatLoginApi.class);
    }

    /**
     * 获取UUID
     *
     * @return
     * @throws IOException
     */
    public String getUUID() throws IOException {
        Call<ResponseBody> responseBodyCall = weChatLoginApi.getUUID(WeChatConstants.APP_ID, "new");
        Response<ResponseBody> response = responseBodyCall.execute();
        String responseBody = response.body()
                                      .string();
        String uuid = RegexUtil.fetch("\"(.*)\"", responseBody);
        this.uuid = uuid;
        return uuid;
    }

    /**
     * 显示登录二维码
     *
     * @param uuid
     */
    public void showQRCode(String uuid) {
        Call<ResponseBody> downloadCall = weChatLoginApi.showQRCode(uuid);
        downloadCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    return;
                }

                try {
                    ResponseBody responseBody = response.body();
                    String url = "https://login.weixin.qq.com/l/" + uuid;
                    QRCodeUtil.printQRCodeInTerminal(url);
                }
                catch (Throwable t) {
                    // TODO Log
                    throw new WeChatException("Download QRCode image failed", t);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                throw new WeChatException("Download QRCode image failed", t);
            }
        });
    }

    public WeChatContext waitForLogin() throws IOException {
        while (true) {
            Call<ResponseBody> waitCall = weChatLoginApi.waitForLogin(true, uuid, 0);
            Response<ResponseBody> waitResponse = waitCall.execute();

            if (waitResponse.isSuccessful()) {
                ResponseBody waitResponseBody = waitResponse.body();
                String waitResponseBodyStr = waitResponseBody.string();
                System.out.println(waitResponseBodyStr);

                String code = RegexUtil.fetch("window.code=(\\d+);", waitResponseBodyStr);
                if (Objects.equals(code, "408")) {
                    // TODO LOG
                    continue;
                }
                else if (Objects.equals(code, "201")) {
                    // TODO LOG
                    // 扫描二维码成功
                    System.out.println("扫描二维码成功，等待在手机上点击确认按钮");
                }
                else if (Objects.equals(code, "200")) {
                    String redirectUri = RegexUtil.fetch("window.redirect_uri=\"(.*)\";", waitResponseBodyStr);
                    redirectUri += "&fun=new";
                    String loginResult = weChatLoginApi.login(redirectUri)
                                                       .execute()
                                                       .body()
                                                       .string();
                    System.out.println(loginResult);

                    //
                    // 更改BaseURL
                    //
                    String weChatDefaultHost = HttpUtil.getSchemeWithHost(redirectUri);
                    apiFactory.setBaseUrl(weChatDefaultHost);
                    weChatLoginApi = apiFactory.create(WeChatLoginApi.class);

                    String skey = RegexUtil.fetch("<skey>(\\S+)</skey>", loginResult);
                    String wxsid = RegexUtil.fetch("<wxsid>(\\S+)</wxsid>", loginResult);
                    String wxuin = RegexUtil.fetch("<wxuin>(\\S+)</wxuin>", loginResult);
                    String passTicket = RegexUtil.fetch("<pass_ticket>(\\S+)</pass_ticket>", loginResult);

                    this.weChatContext = new WeChatContext();
                    weChatContext.setSkey(skey);
                    weChatContext.setSid(wxsid);
                    weChatContext.setUin(Long.valueOf(wxuin));
                    weChatContext.setPassTicket(passTicket);
                    weChatContext.setWeChatHosts(WeChatUtil.generateWeChatHosts(weChatDefaultHost));

                    System.out.println(weChatContext);

                    break;
                }
            }
        }
        return weChatContext;
    }

    public void init() throws IOException {
        Call<InitResponse> responseCall = weChatLoginApi.init(weChatContext.getPassTicket(), weChatContext.getSkey(),
                new InitRequest(weChatContext.getUin(), weChatContext.getSid(), weChatContext.getSkey()));
        Response<InitResponse> response = responseCall.execute();
        InitResponse initResponse = response.body();
        weChatContext.setSelf(initResponse.getUser());
    }
}
