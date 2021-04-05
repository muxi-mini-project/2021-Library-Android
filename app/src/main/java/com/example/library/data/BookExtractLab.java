package com.example.library.data;


import android.content.Context;

import com.example.library.BookExtract.BookDigestData;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//模仿队友BookLab创建BookExtractLab数组单例。
public class BookExtractLab {
    public static BookExtractLab sBookExtractLab;

    private List<BookDigestData.DataDTO> mBookExtracters;

    public BookExtractLab(Context context) {
        mBookExtracters = new ArrayList<>();
        //生成临时数据组
        for (int i = 0; i < 100; i++) {
            BookDigestData.DataDTO bookExtracter = new BookDigestData.DataDTO(context);
            bookExtracter.setTitle("BookExtract #" + i);
            bookExtracter.setSummary_information("context" + i);
            bookExtracter.setDate("Date" + i);
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
    public List<BookDigestData.DataDTO> getBookExtracters() {
        return mBookExtracters;
    }

    public BookDigestData.DataDTO getBookextract(UUID id) {
        for (BookDigestData.DataDTO bookExtracter : mBookExtracters) {
            if (bookExtracter.getBook_id().equals(id)) {
                return bookExtracter;
            }
        }
        return null;
    }
}