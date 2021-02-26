package com.example.library.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentLab {
    private static CommentLab sCommentLab;
    private List<CommentDetail> mCommentDetailList;
    private CommentDetail mCommentDetail;

    private CommentLab(Context context){
        mCommentDetailList = new ArrayList<>();
        for (int j = 0 ; j < 50 ; j ++){
            mCommentDetail.setCommentName("Commenter " + j );
            mCommentDetail.setComment("I think you are right.");
        }
    }
//获取单例。没有就创建一个
    public static CommentLab get(Context context) {
        if (sCommentLab == null) {
            sCommentLab = new CommentLab(context);
        }
        return sCommentLab;
    }


    public List<CommentDetail> getCommentDetailList() {
        return mCommentDetailList;
    }

    /*public CommentDetail getComment(UUID commentId){
        for (CommentDetail e: mCommentDetailList){
            if (commentId == e.getId()){
                return e;
            }
        }
        return null;
    }*/

}
