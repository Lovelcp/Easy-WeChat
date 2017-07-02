package com.wooyoo.wechat.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wooyoo.wechat.http.SimpleCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

public class ApiFactory {
    private OkHttpClient okHttpClient;
    private Retrofit retrofit;

    public ApiFactory(String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        this.okHttpClient = new OkHttpClient.Builder().readTimeout(60, TimeUnit.SECONDS)
                                                      .connectTimeout(60, TimeUnit.SECONDS)
                                                      .cookieJar(new SimpleCookieJar())
                                                      .addInterceptor(logging)
                                                      .build();
        this.retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                                              .addConverterFactory(JacksonConverterFactory.create(
                                                      new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                                                                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE)
                                                                        .setVisibility(PropertyAccessor.FIELD,
                                                                                JsonAutoDetect.Visibility.ANY))) // 配置Json序列化的时候只使用field name，不走getter setter
                                              .client(okHttpClient)
                                              .build();
    }

    public <S> S create(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

    public void setBaseUrl(String baseUrl) {
        retrofit = retrofit.newBuilder()
                           .baseUrl(baseUrl)
                           .build();
    }
}
