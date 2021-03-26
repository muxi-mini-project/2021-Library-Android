package com.example.library.Interface;

import com.example.library.data.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface LoginService {

    @POST("/login")
    Call<Users> getCall(@Body Users users);
}
