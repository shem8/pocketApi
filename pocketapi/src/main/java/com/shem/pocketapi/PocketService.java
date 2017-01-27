package com.shem.pocketapi;

import java.util.Map;

import com.shem.pocketapi.data.Article;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by smagnezi on 13/01/2017.
 */

public interface PocketService {

    @Headers("X-Accept: application/json")
    @FormUrlEncoded
    @POST("v3/oauth/request")
    Call<LoginResponse> login(@Field("consumer_key") String consumerKey, @Field("redirect_uri") String redirectUri);

    class LoginResponse {
        public String code;
    }

    @Headers("X-Accept: application/json")
    @FormUrlEncoded
    @POST("v3/oauth/authorize")
    Call<TokenResponse> getToken(@Field("consumer_key") String consumerKey, @Field("code") String code);

    class TokenResponse {
        public String access_token;
        public String username;
    }


    @Headers("X-Accept: application/json")
    @FormUrlEncoded
    @POST("v3/get")
    Call<ArticlesMap> get(@Field("consumer_key") String consumerKey,
                          @Field("access_token") String token,
                          @Field("state") String state,
                          @Field("count") String count,
                          @Field("sort") String sort,
                          @Field("detailType") String detailType);

    class ArticlesMap {
        public Map<String, Article> list;
    }
}
