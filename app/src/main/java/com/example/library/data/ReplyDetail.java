package com.example.library.data;

public class ReplyDetail {
    String replyName;
    String reply;

    public ReplyDetail(String replyName, String replyContent) {
        this.replyName = replyName;
        this.reply = replyContent;
    }

    //还有回复日期，要不要id？
    public String getReplyName() {
        return replyName;
    }

    public void setReplyName(String replyName) {
        this.replyName = replyName;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
