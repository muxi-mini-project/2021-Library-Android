/*package com.example.library.BookExtract;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookExtract {

    public List<BookExtractData> mBook_extract_list;
    private String message;

    public List<BookExtractData> getBook_extract_list() {
        return mBook_extract_list;
    }

    public void setBook_extract_list() {
        this.mBook_extract_list = mBook_extract_list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "data=" + mBook_extract_list +
                ", message='" + message + '\'' +
                '}';
    }

    public BookExtractData getBook(int id,List<BookExtractData> data){
        for (BookExtractData bookextract:data){
            if (bookextract.Id.equals(id)){
                return bookextract;
            }
        }
        return null;
    }



    public static class BookExtractData {
        private UUID Id;
        private String book_extract_name;
        private String book_extract_id;
        private String book_extract_context;
        private String book_extract_date;
        private Integer click_sum;

        public BookExtractData(Context context) {
            this.Id = UUID.randomUUID();
        }

        public UUID getId() {
            return Id;
        }

        public void setId(UUID id) {
            Id = id;
        }

        public String getBook_extract_context() {
            return book_extract_context;
        }

        public String getBook_extract_date() {
            return book_extract_date;
        }

        public String getBook_extract_id() {
            return book_extract_id;
        }

        public String getBook_extract_name() {
            return book_extract_name;
        }

        public void setBook_extract_context(String book_extract_context) {
            this.book_extract_context = book_extract_context;
        }

        public void setBook_extract_date(String book_extract_date) {
            this.book_extract_date = book_extract_date;
        }

        public void setBook_extract_id(String book_extract_id) {
            this.book_extract_id = book_extract_id;
        }

        public void setBook_extract_name(String book_extract_name) {
            this.book_extract_name = book_extract_name;
        }

        public Integer getClick_sum() {
            return click_sum;
        }

        public void setClick_sum(Integer click_sum) {
            this.click_sum = click_sum;
        }
    }
}*/


