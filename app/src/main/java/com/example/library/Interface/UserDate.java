package com.example.library.Interface;

import com.example.library.data.User;
import com.example.library.data.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserDate {

    @GET("/homepage/info")
    Call<Users> getCall(
            @Header("token") String token
    );


}
