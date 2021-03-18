package com.example.library;

import retrofit2.Call;

import com.example.library.data.BookData;
import com.example.library.data.CommentData;
import com.example.library.data.OthersDigestData;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BookService {
    //@GET注解：采用Get方法发送网络请求
    @GET("library/")
    Call<BookData> getCall();
    //getCall（） = 接受 网络请求数据的方法
    //其中返回类型为Call<*>,*是接收数据的类

    @GET("Library/{books_id}/digest")
    Call<List<OthersDigestData>> getCall2(@Path("books_id") String books_id);

}
