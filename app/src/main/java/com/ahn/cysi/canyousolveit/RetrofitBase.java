package com.ahn.cysi.canyousolveit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitBase {

    String url = "http://172.30.1.54/ahw/cysi/";

    @FormUrlEncoded
    @POST("login")
    Call<RetrofitMember> login(@Field("email") String email,
                               @Field("pwd") String pwd);

    @GET("load_quiz_list")
    Call<RetrofitQuizList> load();
}
