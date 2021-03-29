package com.example.library.BookExtract;

import com.google.gson.annotations.SerializedName;

public class BookDigestData {

    /**
     * data : {"id":7,"user_id":0,"book_id":0,"class_id":0,"title":"","chapter":"","summary_information":"","thought":"","date":"2021-03-28 12:34:46","public":false}
     * message : 创建成功
     */

    private DataDTO data;
    private String message;

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
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
         * id : 7
         * user_id : 0
         * book_id : 0
         * class_id : 0
         * title :
         * chapter :
         * summary_information :
         * thought :
         * date : 2021-03-28 12:34:46
         * public : false
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
        private Boolean publicX;

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

        public Boolean getPublicX() {
            return publicX;
        }

        public void setPublicX(Boolean publicX) {
            this.publicX = publicX;
        }
    }
}
