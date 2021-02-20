package com.example.library.data;

import com.example.library.data.Book;

import java.util.UUID;

public class MyBook extends Book {
    public boolean mIsMine;

    private UUID nId;

    public MyBook(){
        nId = UUID.randomUUID();
    }

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
