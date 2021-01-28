package com.example.library.data;

public class Book {
    public String mBookTitle;
    public String mBookWriter;
    public String mIntroduction;

    public Book(String bookTitle, String bookWriter, String introduction) {
        mBookTitle = bookTitle;
        mBookWriter = bookWriter;
        mIntroduction = introduction;
    }

    public String getBookTitle() {
        return mBookTitle;
    }

    public void setBookTitle(String bookTitle) {
        mBookTitle = bookTitle;
    }

    public String getBookWriter() {
        return mBookWriter;
    }

    public void setBookWriter(String bookWriter) {
        mBookWriter = bookWriter;
    }

    public String getIntroduction() {
        return mIntroduction;
    }

    public void setIntroduction(String introduction) {
        mIntroduction = introduction;
    }
}
