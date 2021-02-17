package com.example.library;

public class book_extract {
    private String bookname;
    private int contextId;
    private int dateId;

    public book_extract(String bookname, int contextId, int dateId) {
        this.bookname = bookname;
        this.contextId = contextId;
        this.dateId = dateId;

    }
    public String getBookname(){
        return bookname;
    }
    public int getContextId(){
        return contextId;
    }
    public int getDateId(){
        return dateId;
    }
}