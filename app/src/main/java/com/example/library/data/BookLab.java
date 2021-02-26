package com.example.library.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//模仿CrimeLab创建BookLab数组单例。暂定
public class BookLab {
    public static BookLab sBookLab;
    private List<Book> mBooks;
    private List<MyBook> mMyBooks;

    private BookLab(Context context){
        mBooks = new ArrayList<>();
        mMyBooks = new ArrayList<>();
        //生成临时数据组
        for (int i = 0; i < 100; i++){
            Book book = new Book();
            book.setBook_name("Book #" + i);
            book.setBook_author("writer" + i);
            book.setBook_information("I am book" + i);
            mBooks.add(book);
        }

        for(int j = 0; j<100; j++){
            MyBook myBook = new MyBook();
            myBook.setBook_name("MyBook #"+j);
            myBook.setBook_author("writer"+j);
            myBook.setBook_information("I am mybook" +j);
            mMyBooks.add(myBook);
        }
    }

    //创建单例
    public static BookLab get(Context context) {
        if (sBookLab == null) {
            sBookLab = new BookLab(context);
        }
        return sBookLab;
    }

    //得到数组
    public List<Book> getBooks(){
        return mBooks;
    }

    //暂时测试书城的书，无我的书
    public Book getBook(UUID id){
        for (Book book:mBooks){
            if (book.getId().equals(id)){
                return book;
            }
        }
        return null;
    }

    public List<MyBook> getmMyBooks(){
        return mMyBooks;
    }
    public MyBook getMyBook(UUID Id) {
        for (MyBook myBook : mMyBooks) {
            if (myBook.getId().equals(Id)) {
                return myBook;
            }
        }
        return null;
    }
}
