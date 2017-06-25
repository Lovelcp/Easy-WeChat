package com.wooyoo.wechat;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.github.com/")
                                                  .addConverterFactory(JacksonConverterFactory.create(
                                                          new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)))
                                                  .build();

        GitHubService service = retrofit.create(GitHubService.class);

        Call<List<Repo>> repos = service.listRepos("octocat");
        repos.execute().body().forEach(repo -> System.out.println(repo.getId()));
        retrofit = retrofit.newBuilder().baseUrl("https://www.baidu.com").build();
        service.listRepos("octocat").execute().body().forEach(repo -> System.out.println(repo.getId()));

    }
}
