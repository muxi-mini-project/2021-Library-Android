package com.example.library.data;


import android.content.Context;
import com.example.library.BookExtract.BookExtract;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//模仿队友BookLab创建BookExtractLab数组单例。
public class BookExtractLab {
    public static BookExtractLab sBookExtractLab;

    private List<BookExtract.BookExtractData> mBookExtracters;

    public BookExtractLab(Context context) {
        mBookExtracters = new ArrayList<>();
        //生成临时数据组
        for (int i = 0; i < 100; i++) {
            BookExtract.BookExtractData bookExtracter = new BookExtract.BookExtractData(context);
            bookExtracter.setBook_extract_name("BookExtract #" + i);
            bookExtracter.setBook_extract_context("context" + i);
            bookExtracter.setBook_extract_date("Date" + i);
            mBookExtracters.add(bookExtracter);
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
    public List<BookExtract.BookExtractData> getBookExtracters() {
        return mBookExtracters;
    }

    public BookExtract.BookExtractData getBookextract(UUID id) {
        for (BookExtract.BookExtractData bookExtracter : mBookExtracters) {
            if (bookExtracter.getBook_extract_id().equals(id)) {
                return bookExtracter;
            }
        }
        return null;
    }
}