package com.example.library.BookExtract;

import android.content.Context;
import android.widget.EditText;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.io.SerializablePermission;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class BookDigestData implements Serializable {
    /**
     * data : {"id":7,"user_id":0,"book_id":0,"class_id":0,"title":"",
     * "chapter":"","summary_information":"",
     * "thought":"",
     * "date":"2021-03-28 12:34:46",
     * "public":false}
     * message : 创建成功
     */
    private String message;
    public List<DataDTO> data;

    public List<DataDTO> getBook_extract_list() {
        return data;
    }

    public void setBook_extract_list(List<DataDTO> data) {
        this.data=data;
    }

    public DataDTO getBook(int id,List<DataDTO> data){
        for (DataDTO bookextract:data){
            if (bookextract.Id.equals(id)){
                return bookextract;
            }
        }
        return null;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataDTO implements Serializable {
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
        private UUID Id;
        private Integer id;
        private Integer user_id;
        private String book_id;
        private Integer class_id;
        private String title;
        private String chapter;
        private String summary_information;
        private String thought;
        private String date;
        @SerializedName("public")
        private Boolean publicX;

        public DataDTO() {
            this.Id = UUID.randomUUID();
        }

        public void setId(UUID id) {
            Id = id;
        }

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

        public String getBook_id() {
            return book_id;
        }

        public void setBook_id(String book_id) {
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
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
            Calendar c=Calendar.getInstance();
            String date1=sdf.format(c.getTime());
            return date1;
        }

        public void setDate(String date1) {
            this.date = date1;
        }

        public Boolean getPublicX() {
            return publicX;
        }

        public void setPublicX(Boolean publicX) {
            this.publicX = publicX;
        }
    }
}
