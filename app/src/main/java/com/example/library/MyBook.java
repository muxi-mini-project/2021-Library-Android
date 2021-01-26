package com.example.library;

public class MyBook extends Book {
    public boolean mIsMine;

    public MyBook(String bookTitle, String bookWriter, String introduction) {
        super(bookTitle, bookWriter, introduction);
    }

    public boolean isMine() {
        return mIsMine;
    }

    public void setMine(boolean mine) {
        mIsMine = mine;
    }
}
