package com.example.library;

import java.util.Date;

public class MyNotes extends Notes{
    public boolean mIsMyNotes;

    public MyNotes(String noteTitle, String noteContent, Date noteDate) {
        super(noteTitle, noteContent, noteDate);
    }

    public boolean isMyNotes() {
        return mIsMyNotes;
    }

    public void setMyNotes(boolean myNotes) {
        mIsMyNotes = myNotes;
    }
}
