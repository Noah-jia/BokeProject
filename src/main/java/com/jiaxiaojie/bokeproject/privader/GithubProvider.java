package com.jiaxiaojie.bokeproject.privader;

import com.alibaba.fastjson.JSON;
import com.jiaxiaojie.bokeproject.domain.AccessTokenDto;
import com.jiaxiaojie.bokeproject.domain.GithubUser;
import okhttp3.*;
import okio.BufferedSink;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDto accessTokenDto){
        MediaType mediaType=MediaType.get("application/json;charset=utf-8");
        OkHttpClient client=new OkHttpClient();
        RequestBody body=RequestBody.create(mediaType, JSON.toJSONString(accessTokenDto));
        Request request=new Request.Builder()
                         .url("https://github.com/login/oauth/access_token")
                          .post(body)
                          .build();
       try(Response response=client.newCall(request).execute()) {
         String string=response.body().string();
         String token=string.split("&")[0].split("=")[1];
           System.out.println(token);
           return token;
       }catch (Exception e){

       }
       return null;
    }
    public GithubUser getUser(String accessToken){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url("https://api.github.com/user?access_token="+accessToken).build();
      try {
          Response response=client.newCall(request).execute();
          String string=response.body().string();
          GithubUser githubUser=JSON.parseObject(string,GithubUser.class);
          System.out.println(githubUser);
          return githubUser;
      }catch (Exception e){

      }
      return null;
    }

}
