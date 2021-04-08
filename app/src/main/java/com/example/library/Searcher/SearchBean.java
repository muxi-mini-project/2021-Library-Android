package com.example.library.Searcher;

public class SearchBean {

    /**
     * book_name : string
     */
    private String book_name;

    public SearchBean(String book_name) {
        this.book_name = book_name;
    }

    public SearchBean(int icon, String s, String 自定义搜索view, String s1) {

    }
        public String getBook_name () {
            return book_name;
        }

        public void setBook_name (String book_name){
            this.book_name = book_name;
        }

    }

