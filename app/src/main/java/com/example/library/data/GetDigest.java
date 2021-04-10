package com.example.library.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetDigest {

    /**
     * data : {"id":31,"user_id":9,"book_id":0,"class_id":1,"title":"fu","chapter":"text","summary_information":"kjsdfhkffsfsf","thought":"omg","date":"2021-04-10 14:42:01","public":"ture"}
     * message : 创建成功
     */

    private List<DataDTO> data;
    private String message;

    public List<DataDTO> getData() {
        return data;
    }

    public void setData(List<DataDTO> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO {
        /**
         * id : 31
         * user_id : 9
         * book_id : 0
         * class_id : 1
         * title : fu
         * chapter : text
         * summary_information : kjsdfhkffsfsf
         * thought : omg
         * date : 2021-04-10 14:42:01
         * public : ture
         */

        private Integer id;
        private Integer user_id;
        private Integer book_id;
        private Integer class_id;
        private String title;
        private String chapter;
        private String summary_information;
        private String thought;
        private String date;
        @SerializedName("public")
        private String publicX;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getUser_id() {
            return user_id;
        }

        public void setUser_id(Integer user_id) {
            this.user_id = user_id;
        }

        public Integer getBook_id() {
            return book_id;
        }

        public void setBook_id(Integer book_id) {
            this.book_id = book_id;
        }

        public Integer getClass_id() {
            return class_id;
        }

        public void setClass_id(Integer class_id) {
            this.class_id = class_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getChapter() {
            return chapter;
        }

        public void setChapter(String chapter) {
            this.chapter = chapter;
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

        public String getPublicX() {
            return publicX;
        }

        public void setPublicX(String publicX) {
            this.publicX = publicX;
        }
    }
}
