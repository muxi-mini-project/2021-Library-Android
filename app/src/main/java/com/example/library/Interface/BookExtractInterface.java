package com.example.library.Interface;

import com.example.library.BookExtract.BookDigestData;
import com.example.library.BookExtract.BookExtract;
import com.example.library.edit_item_java;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookExtractInterface {
    //@GET注解：采用Get方法发送网络请求
    @GET("digest/mysummary/{user_id}")
   // Call<BookExtract> getCall();
     Call<BookExtract> getCall(@Path("user_id")String user_id);

    //getCall（） = 接受 网络请求数据的方法
    //其中返回类型为Call<*>,*是接收数据的类
    @GET("/digest/mysummary/{user_id}/classes")
    Call<edit_item_java> getCall2();
    // Call<edit_item_java> getCall2(@Path("user_id")String user_id);

    @PUT("/digest/person/{summary_id}")
    Call<edit_item_java> getPut(@Path ("summary_id") String summary_id);

    @GET("/digest/mysummary/{user_id}/classes_edit")
    Call<edit_item_java> getCall3(@Path("user_id")String user_id);

    @DELETE("/digest/mysummary/{user_id}/clasees_edit")
    Call<edit_item_java> getDelete(@Path("user_id")String user_id);

    @POST("/digest")
    Call<BookDigestData> getDigest();

}
