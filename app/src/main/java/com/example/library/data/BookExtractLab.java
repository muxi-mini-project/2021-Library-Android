package com.example.library.data;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//模仿队友BookLab创建BookExtractLab数组单例。
public class BookExtractLab {
    public static BookExtractLab sBookExtractLab;
    //private final MyBookExtract myBookExtracts;
    private List<BookExtracter> mBookExtracters;
    private List<MyBookExtract> mMyBookExtracts;

    private BookExtractLab(Context context) {
        mBookExtracters = new ArrayList<>();
        mMyBookExtracts = new ArrayList<>();
        //生成临时数据组
        for (int i = 0; i < 100; i++) {
            BookExtracter bookExtracter = new BookExtracter();
            bookExtracter.setBookextract_name("BookExtract #" + i);
            bookExtracter.setBookextract_author("writer" + i);
            bookExtracter.setBookextract_information("I am bookextract" + i);
            mBookExtracters.add(bookExtracter);
        }

        for (int j = 0; j < 100; j++) {
            MyBookExtract myBookExtract = new MyBookExtract();
            myBookExtract.setBookextract_name("MyBook #" + j);
            myBookExtract.setBookextract_author("writer" + j);
            myBookExtract.setBookextract_information("I am mybookextract" + j);
            mMyBookExtracts.add(myBookExtract);
        }
    }

    //创建单例
    public static BookExtractLab get(Context context) {
        if (sBookExtractLab == null) {
            sBookExtractLab = new BookExtractLab(context);
        }
        return sBookExtractLab;
    }

    //得到数组
    public List<BookExtracter> getBookExtracters() {
        return mBookExtracters;
    }

    //暂时测试书城的书，无我的书
    public BookExtracter getBookextract(UUID id) {
        for (BookExtracter bookExtracter : mBookExtracters) {
            if (bookExtracter.getId().equals(id)) {
                return bookExtracter;
            }
        }
        return null;
    }

    public List<MyBookExtract> getmMyBookextracts() {
        return mMyBookExtracts;
    }

    public MyBookExtract getMyBook(UUID Id) {
        for (MyBookExtract myBookExtract : mMyBookExtracts) {
            if (myBookExtract.getBookextract_id().equals(Id)) {
                return myBookExtract;
            }
        }
        return null;
    }
}

