package com.example.library.Interface;

import com.example.library.data.BookData;
import com.example.library.data.MyBook;
import com.example.library.data.MyDigest;
import com.example.library.data.User;
import com.example.library.data.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserDate {

    @GET("/homepage/info")
    Call<Users> getCall(
            @Header("token") String token
    );

    @PUT("/homepage/info")
    Call<User> setCall(
            @Header("token") String token,
            @Body User user
    );


/**
 * 失败
 */
    @GET("/homepage/shelf")
    Call<BookData> getBook(
            @Header("token") String token);

    @GET("/homepage/shelf/{books_id}")
    Call<MyBook> getABook(
            @Header("token") String token,
            @Path("books_id") int number
    );

    /**
     * 失败
     */
    @PUT("/homepage/shelf/{books_id}")
    Call<MyBook> deleteABook(
            @Header("token") String token,
            @Path("books_id") int number
    );

    @GET("/homepage/mydigest")
    Call<MyDigest> getDigest(
            @Header("token") String token
    );

    @GET("homepage/mydigest/{digest_id}")
    Call<MyDigest> getADigest(
            @Header("token") String token,
            @Path("digest_id") int digest_id
    );

    @PUT("homepage/mydigest/{digest_id}")
    Call<MyDigest> deleteADigest(
            @Header("token") String token,
            @Path("digest_id") int digest_id
    );



}
