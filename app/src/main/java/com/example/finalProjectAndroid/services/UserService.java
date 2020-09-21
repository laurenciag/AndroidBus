package com.example.finalProjectAndroid.services;

import com.example.finalProjectAndroid.Entity.User;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserService {
    @Multipart
    @POST("user/login")
    Call<String> login (@Part("email")RequestBody email, @Part("password") RequestBody password);

    @POST("user/createUser")
    Call<User> register(@Body User userInp);

    @POST("user/checkEmailUserByUser")
    Call<User> checkEmail(@Body User userParam);

    @Multipart
    @POST("user/getUserById")
    Call<User> getUserById(@Part("userId") RequestBody userId);
}
