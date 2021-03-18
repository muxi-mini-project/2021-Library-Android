package com.example.library.data;

import com.google.gson.annotations.SerializedName;

public class OthersDigestData {
    /**
     * id :
     * user_id : 1
     * book_id : 1
     * class_id : 1
     * title : 离散数学
     * summary_information : 123
     * thought : 123
     * date : 2021-03-16T23:51:18+08:00
     * public : true
     */

    private String id;
    private String user_id;
    private String book_id;
    private String class_id;
    private String title;
    private String summary_information;
    private String thought;
    private String date;
    @SerializedName("public")
    private Boolean publicX;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary_information() {
        return summary_information;
    }

    public void setSummary_information(String summary_information) {
        this.summary_information = summary_information;
    }

    public String getThought() {
        return thought;
    }

    public void setThought(String thought) {
        this.thought = thought;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Boolean getPublicX() {
        return publicX;
    }

    public void setPublicX(Boolean publicX) {
        this.publicX = publicX;
    }
}
