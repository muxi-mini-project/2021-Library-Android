package com.example.library.Interface;

import com.example.library.data.BookDigestData;
import com.example.library.Searcher.SearchView2;
import com.example.library.data.GetDigest;
import com.example.library.edit_item_java;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BookExtractInterface {
    //@GET注解：采用Get方法发送网络请求
    //书摘主界面
    @GET("/digest/mysummary/{user_id}")
   // Call<BookExtract> getCall();
     Call<GetDigest> getCall(
             @Header("token") String token,
             @Path("user_id")String user_id
    );

    //getCall（） = 接受 网络请求数据的方法
    //其中返回类型为Call<*>,*是接收数据的类
    //书摘分类
    @GET("/digest/mysummary/{user_id}/classes")
   // Call<edit_item_java> getCall2();
     Call<edit_item_java> getCall2(@Path("user_id")String user_id);

    //是否公开
    @PUT("/digest/mysummary/{user_id}")
    Call<BookDigestData> getPut(
            @Path ("user_id") String user_id,
           @Header("token")String token
    );

    //获取书摘分类
    @GET("/digest/mysummary/{user_id}/classes_edit")
    Call<edit_item_java> getCall3(@Path("user_id")String user_id);

    //删除书摘分类
    @DELETE("/digest/mysummary/{user_id}/clasees_edit")
    Call<edit_item_java> getDelete(@Path("user_id")String user_id);

    //编辑书摘
    @PUT("/digest/person/{summary_id}")
    Call<BookDigestData> putdigest(
            @Path("summary_id")String summary_id,
            @Header("token")String token
           // @Body("summaryInfo")Object summaryInfo
    );

    //添加书摘
    @POST("/digest")
    Call<BookDigestData.DataDTO> getDigest(
            @Header("token") String token,
            @Body BookDigestData.DataDTO bookDigestData

    );

    //搜索
    @POST("/digest/mysummary/{user_id}")
    Call<SearchView2> getpost(
            @Path("user_id")String user_id,
              @Header("token")String token
    );

}
