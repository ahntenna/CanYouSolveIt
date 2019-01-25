package com.ahn.cysi.canyousolveit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface RetrofitBase {

    String url = "http://172.30.1.40/ahw/cysi/";

    @FormUrlEncoded
    @POST("/login.php")
    Call<RetrofitMember> login(@Field("email") String email,
                               @Field("pwd") String pwd);
}
