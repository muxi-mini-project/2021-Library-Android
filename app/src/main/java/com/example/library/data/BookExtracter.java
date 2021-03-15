package com.example.library.data;

import java.util.UUID;

public class BookExtracter {
    private UUID Id;
    private String bookextract_author;
    private String bookextract_id;
    private String bookextract_information;
    private String bookextract_name;
    private Integer click_sum;

    public BookExtracter(){
        this.Id = UUID.randomUUID();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getBookextract_author() {
        return bookextract_author;
    }

    public void setBookextract_author(String bookextract_author) {
        this.bookextract_author = bookextract_author;
    }

    public String getBookextract_id() {
        return bookextract_id;
    }

    public void setBookextract_id(String bookextract_id) {
        this.bookextract_id = bookextract_id;
    }

    public String getBookextract_information() {
        return bookextract_information;
    }

    public void setBookextract_information(String bookextract_information) {
        this.bookextract_information = bookextract_information;
    }

    public String getBookextract_name() {
        return bookextract_name;
    }

    public void setBookextract_name(String bookextract_name) {
        this.bookextract_name = bookextract_name;
    }

    public Integer getClick_sum() {
        return click_sum;
    }

    public void setClick_sum(Integer click_sum) {
        this.click_sum = click_sum;
    }

  //  public int get(int position) {
   //     return position;
  //  }
}

