package com.example.library.BookExtract;

import java.util.UUID;

public class BookextractList {

    private UUID Id;
    private String book_extract_name;
    private String book_extract_id;
    private String book_extract_context;
    private String book_extract_date;
    private Integer click_sum;

    public BookextractList(){
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
