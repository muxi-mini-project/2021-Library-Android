package com.example.library.Interface;

import retrofit2.Call;

import com.example.library.data.BookData;
import com.example.library.data.CommentData;
import com.example.library.data.CommentPut;
import com.example.library.data.OthersDigestData;
import com.example.library.data.PostSearch;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

<<<<<<< HEAD

=======
    @GET("library/ranking")
    Call<BookData> getRankCall();
//返回评论
    @GET("Library/{books_id}/digest/{digest_id}")
    Call<List<CommentData>> getCommentCall(
            @Header ("token")String token,
            @Path("books_id") String books_id,
            @Path("digest_id") String digest_id
    );

    @PUT("Library/{books_id}/digest/{digest_id}/review")
    Call<CommentPut> putComment(
            @Header("token") String token,
            @Path("books_id") String books_id,
            @Path("digest_id") String digest_id,
            @Body CommentPut content
    );

    @POST("Library/searcher")
    Call<BookData> getSearchCall(@Body PostSearch postSearch);
>>>>>>> e4f6784e781b1eb6e1176b7690b9ea04ee8a2f1e

}
