package com.example.library.data;

public class MyDigest {

    private String id;
    private String user_id;
    private String book_id;
    private String class_id;
    private String title;
    private String chapter;
    private String summary_information;
    private String thought;
    private String date;
    private Boolean Public;

    public String getId(){
        return id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getBook_id() {
        return book_id;
    }

    public String getClass_id() {
        return class_id;
    }

    public String getTitle() {
        return title;
    }

    public String getChapter() {
        return chapter;
    }

    public String getSummary_information() {
        return summary_information;
    }

    public String getThought() {
        return thought;
    }



    public Boolean getPublic() {
        return Public;
    }
}
