package com.wooyoo.wechat;

import com.wooyoo.wechat.constant.URLs;
import com.wooyoo.wechat.exception.WeChatException;
import com.wooyoo.wechat.http.SimpleCookieJar;
import com.wooyoo.wechat.util.QRCodeUtil;
import com.wooyoo.wechat.util.RegexUtil;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        //@see http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0/
        System.setProperty("jsse.enableSNIExtension", "false");
        final OkHttpClient httpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                                                                  .connectTimeout(60, TimeUnit.SECONDS)
                                                                  .cookieJar(new SimpleCookieJar())
                                                                  .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.BASE_LOGIN_URL)
                                                  .client(httpClient)
                                                  .build();

        WeChatHttpService weChatService = retrofit.create(WeChatHttpService.class);
        Call<ResponseBody> responseBodyCall = weChatService.getUUID("wx782c26e4c19acffb", "new");
        Response<ResponseBody> responseBodyResponse = responseBodyCall.execute();
        String getUUIDResult = responseBodyResponse.body()
                                                   .string();
        System.out.println(getUUIDResult);

        String pattern = "\"(.*)\"";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(getUUIDResult);
        if (m.find()) {
            String uuid = m.group(1);
            System.out.println(uuid);

            Call<ResponseBody> downloadCall = weChatService.showQRCode(uuid);
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

            while (true) {
                Call<ResponseBody> waitCall = weChatService.waitForLogin(true, uuid, 0);
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
                        System.out.println("等待在手机上点击确认按钮");
                    }
                    else if (Objects.equals(code, "200")) {
                        String redirectUri = RegexUtil.fetch("window.redirect_uri=\"(.*)\";", waitResponseBodyStr);
                        // TODO 根据redirectUri设置微信的host，比如wx2/wx3这样的
                        redirectUri += "&fun=new";

                        String loginResult = weChatService.login(redirectUri).execute().body().string();
                        System.out.println(loginResult);

                        // TODO 解析登录成功的信息


                        break;
                    }
                }
            }
        }

    }
}
