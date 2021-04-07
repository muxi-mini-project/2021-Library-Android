package com.example.library.data;

import java.util.List;

public class BookData {
    /**
     * data : [{
     *
     * "book_id":53,
     * "book_name":"再见，西贡","
     * book_auther":"[法]雷蒙·德帕尔东/RaymondDepardon/后浪丨九州出版社/2021-3",
     * "book_information":"按时间顺序呈现了在法越战争结束后的1964年、1972\u20141973年越战末期、2014年西贡解放40周年之际德帕尔东在越南拍摄的照片。","
     * book_picture":"https://img1.doubanio.com/view/subject/s/public/s33823579.jpg",
     * "class_id":0,
     * "click_sum":0},
     * {
     *
     * "book_id":52,
     * "book_name":"重归一统",
     * "book_auther":"(美)龙沛/后浪丨九州出版社/2021-3",
     * "book_information":"本书讨论了战争和政治在宋朝建立过程中发挥的作用，批驳了\u201c宋朝开国是一个非军事化过程，宋朝自建立之初就重文轻武\u201d的刻板印象。",
     * "book_picture":"https://img9.doubanio.com/view/subject/s/public/s33835056.jpg",
     * "class_id":0,
     * "click_sum":0},
     *
     * {
     * "book_id":51,"book_name":"2000年以来的西方",
     * "book_auther":"刘擎/一頁丨当代世界出版社/2021-3",
     * "book_information":"本书为刘擎教授自2003年到2019年撰写的西方知识界年度回顾，致力于从\u201c内部视角\u201d去观察西方，并为当今中国的公共讨论提供相关背景和线索。",
     * "book_picture":"https://img9.doubanio.com/view/subject/s/public/s33837965.jpg","class_id":0,"click_sum":0},
     *
     */

    private List<DataBean> data;
    private String message;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "data=" + data +
                ", message='" + message + '\'' +
                '}';
    }

    public DataBean getBook(int id,List<DataBean> data){
        for (DataBean book:data){
            if (book.getBook_id().equals(id)){
                return book;
            }
        }
        return null;
    }

    public static class DataBean {
        /**
         * book_id : 53
         * book_name : 再见，西贡
         * book_auther : [法]雷蒙·德帕尔东/RaymondDepardon/后浪丨九州出版社/2021-3
         * book_information : 按时间顺序呈现了在法越战争结束后的1964年、1972—1973年越战末期、2014年西贡解放40周年之际德帕尔东在越南拍摄的照片。
         * book_picture : https://img1.doubanio.com/view/subject/s/public/s33823579.jpg
         * class_id : 0
         * click_sum : 0
         */

        private Integer book_id;
        private String book_name;
        private String book_auther;
        private String book_information;
        private String book_picture;
        private Integer class_id;
        private Integer click_sum;

        public Integer getBook_id() {
            return book_id;
        }

        public void setBook_id(Integer book_id) {
            this.book_id = book_id;
        }

        public String getBook_name() {
            return book_name;
        }

        public void setBook_name(String book_name) {
            this.book_name = book_name;
        }

        public String getBook_auther() {
            return book_auther;
        }

        public void setBook_auther(String book_auther) {
            this.book_auther = book_auther;
        }

        public String getBook_information() {
            return book_information;
        }

        public void setBook_information(String book_information) {
            this.book_information = book_information;
        }

        public String getBook_picture() {
            return book_picture;
        }

        public void setBook_picture(String book_picture) {
            this.book_picture = book_picture;
        }

        public Integer getClass_id() {
            return class_id;
        }

        public void setClass_id(Integer class_id) {
            this.class_id = class_id;
        }

        public Integer getClick_sum() {
            return click_sum;
        }

        public void setClick_sum(Integer click_sum) {
            this.click_sum = click_sum;
        }


    }
}
