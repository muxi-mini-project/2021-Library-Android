package com.example.library.data;

import java.util.List;
import java.util.UUID;

public class MyBook {

    public int book_id;
    public String book_name;
    public String book_auther;
    public String book_information;
    public String book_picture;
    public int class_id;
    public int click_sum;
    public List<MyBook> date;


    public int getBook_id() {
        return book_id;
    }

    public String getBook_name(){
        return book_name;
    }

    public String getBook_auther() {
        return book_auther;
    }

    public String getBook_information() {
        return book_information;
    }

    public String getBook_picture() {
        return book_picture;
    }

    public int getClass_id() {
        return class_id;
    }

    public int getClick_sum() {
        return click_sum;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public void setBook_auther(String book_auther) {
        this.book_auther = book_auther;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_information(String book_information) {
        this.book_information = book_information;
    }

    public void setBook_picture(String book_picture) {
        this.book_picture = book_picture;
    }

    public void setClass_id(int class_id) {
        this.class_id = class_id;
    }

    public void setClick_sum(int click_sum) {
        this.click_sum = click_sum;
    }

    public List<MyBook> getMyBookDate(){
        return date;
    }
}
