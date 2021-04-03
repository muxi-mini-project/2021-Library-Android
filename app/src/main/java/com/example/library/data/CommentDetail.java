package com.example.library.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class CommentDetail {
    private int Id;
    private String mCommentName;
    private Date mCommentDate;
    private String mComment;
    private List<ReplyDetail> mReplyList;
    private String createDate;
    private int replyTotal;

    public CommentDetail(){

    }

    public CommentDetail(String commentName, String commentContent,String createDate) {
        this.mCommentName = commentName;
        this.mComment = commentContent;
        this.createDate = createDate;
    }

    public int getReplyTotal() {
        return replyTotal;
    }

    public void setReplyTotal(int replyTotal) {
        this.replyTotal = replyTotal;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setId(int id) {
        Id = id;
    }

    public List<ReplyDetail> getReplyList() {
        return mReplyList;
    }

    public void setReplyList(List<ReplyDetail> replyList) {
        mReplyList = replyList;
    }

    public int getId() {
        return Id;
    }

    public String getCommentName() {
        return mCommentName;
    }

    public void setCommentName(String commentName) {
        mCommentName = commentName;
    }

    public String getCommentDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String t=format.format(mCommentDate);
        return t;
    }

    public void setCommentDate(Date commentDate) {
        mCommentDate = commentDate;
    }

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }
}
