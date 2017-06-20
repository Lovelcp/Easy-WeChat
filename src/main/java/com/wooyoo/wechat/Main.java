package com.wooyoo.wechat;

import com.wooyoo.wechat.constant.URLs;
import com.wooyoo.wechat.exception.WeChatException;
import com.wooyoo.wechat.util.QRCodeUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {

        //@see http://stackoverflow.com/questions/7615645/ssl-handshake-alert-unrecognized-name-error-since-upgrade-to-java-1-7-0/
        System.setProperty("jsse.enableSNIExtension", "false");
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URLs.BASE_LOGIN_URL)
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

            Call<ResponseBody> downloadCall = weChatService.downloadQRCode(uuid);
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
                        throw new WeChatException("Download QRCode image failed", t);
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    throw new WeChatException("Download QRCode image failed", t);
                }
            });
        }

    }
}
