package com.ahn.cysi.canyousolveit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitBase {

    String url = "http://169.254.30.5/ahw/cysi/";

    @FormUrlEncoded
    @POST("login")
    Call<RetrofitMember> login(@Field("email") String email,
                               @Field("pwd") String pwd);
}
