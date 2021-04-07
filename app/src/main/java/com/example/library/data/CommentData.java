package com.example.library.data;

public class CommentData {

    /**
     * review_id : 5
     * user_id : 7
     * summary_id : 12
     * content : 这是一本好书啊，您说的真的好对。
     */

    private Integer review_id;
    private String user_id;
    private String summary_id;
    private String content;

    public CommentData(String name, String commentContent) {
        user_id = name;
        content = commentContent;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public void setReview_id(Integer review_id) {
        this.review_id = review_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSummary_id() {
        return summary_id;
    }

    public void setSummary_id(String summary_id) {
        this.summary_id = summary_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
