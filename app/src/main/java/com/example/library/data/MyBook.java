package com.example.library.data;

import java.util.UUID;

public class MyBook {

    public boolean mIsMine;
    private UUID mId;
    public String mBookTitle;
    public String mBookWriter;
    public String mIntroduction;

    //测试用的数据构造器。循环生成，不添加参数
    public MyBook(){
        mId = UUID.randomUUID();
    }

    public MyBook(String bookTitle, String bookWriter, String introduction) {
        mId = UUID.randomUUID();
        mBookTitle = bookTitle;
        mBookWriter = bookWriter;
        mIntroduction = introduction;
    }

    public UUID getId() {
        return mId;
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

    public boolean isMine() {
        return mIsMine;
    }

    public void setMine(boolean mine) {
        mIsMine = mine;
    }

    public void setBook_name(String s) {
    }
}
