package com.example.library.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

//模仿CrimeLab创建BookLab数组单例。暂定
public class BookLab {
    public static BookLab sBookLab;
    private List<Book> mBooks;
    private List<MyBook> mMyBooks;

    private BookLab(Context context){
        mBooks = new ArrayList<>();
        mMyBooks = new ArrayList<>();
    }

    //创建单例
    public static BookLab get(Context context) {
        if (sBookLab == null) {
            sBookLab = new BookLab(context);
        }
        return sBookLab;
    }
}
