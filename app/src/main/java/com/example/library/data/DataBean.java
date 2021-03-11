package com.example.library.data;

import java.util.UUID;

public class Book {
    /**
     * book_author : string
     * book_id : string
     * book_information : string
     * book_name : string
     * book_picture : string
     * class_id : string
     * click_sum : 0
     */
    private UUID Id;
    private String book_author;
    private String book_id;
    private String book_information;
    private String book_name;
    private String book_picture;
    private String class_id;
    private Integer click_sum;

    public Book(){
        this.Id = UUID.randomUUID();
    }

    public UUID getId() {
        return Id;
    }

    public void setId(UUID id) {
        Id = id;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getBook_information() {
        return book_information;
    }

    public void setBook_information(String book_information) {
        this.book_information = book_information;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_picture() {
        return book_picture;
    }

    public void setBook_picture(String book_picture) {
        this.book_picture = book_picture;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public Integer getClick_sum() {
        return click_sum;
    }

    public void setClick_sum(Integer click_sum) {
        this.click_sum = click_sum;
    }
}
