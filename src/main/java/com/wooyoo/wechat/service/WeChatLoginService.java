package com.wooyoo.wechat.service;

import com.wooyoo.wechat.constant.WeChatConstants;
import com.wooyoo.wechat.exception.WeChatException;
import com.wooyoo.wechat.http.WeChatSession;
import com.wooyoo.wechat.http.api.WeChatLoginApi;
import com.wooyoo.wechat.http.request.InitRequest;
import com.wooyoo.wechat.http.response.InitResponse;
import com.wooyoo.wechat.util.HttpUtil;
import com.wooyoo.wechat.util.QRCodeUtil;
import com.wooyoo.wechat.util.RegexUtil;
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
    protected WeChatSession weChatSession;
    protected ApiGenerator apiGenerator;
    protected String uuid;

    public WeChatLoginService() {
        this.apiGenerator = new ApiGenerator();
        this.weChatLoginApi = apiGenerator.create(WeChatLoginApi.class);
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

    public WeChatSession waitForLogin() throws IOException {
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
                    // TODO 根据redirectUri设置微信的host，比如wx2/wx3这样的
                    redirectUri += "&fun=new";

                    String loginResult = weChatLoginApi.login(redirectUri)
                                                       .execute()
                                                       .body()
                                                       .string();
                    System.out.println(loginResult);

                    //
                    // 更改BaseURL
                    //
                    apiGenerator.changeBaseUrl(HttpUtil.getSchemeWithHost(redirectUri));
                    weChatLoginApi = apiGenerator.create(WeChatLoginApi.class);

                    String skey = RegexUtil.fetch("<skey>(\\S+)</skey>", loginResult);
                    String wxsid = RegexUtil.fetch("<wxsid>(\\S+)</wxsid>", loginResult);
                    String wxuin = RegexUtil.fetch("<wxuin>(\\S+)</wxuin>", loginResult);
                    String passTicket = RegexUtil.fetch("<pass_ticket>(\\S+)</pass_ticket>", loginResult);

                    this.weChatSession = new WeChatSession();
                    weChatSession.setSkey(skey);
                    weChatSession.setSid(wxsid);
                    weChatSession.setUin(Long.valueOf(wxuin));
                    weChatSession.setPassTicket(passTicket);

                    System.out.println(weChatSession);

                    break;
                }
            }
        }
        return weChatSession;
    }

    public void init() throws IOException {
        Call<InitResponse> responseCall = weChatLoginApi.init(weChatSession.getPassTicket(), weChatSession.getSkey(),
                new InitRequest(weChatSession.getUin(), weChatSession.getSid(), weChatSession.getSkey()));

        Response<InitResponse> response = responseCall.execute();

        InitResponse initResponse = response.body();

        int a = 0;
    }
}
