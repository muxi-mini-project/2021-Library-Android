package com.example.library.data;

import java.util.Date;
import java.util.UUID;

public class MyNotes extends Notes{
    public boolean mIsMyNotes;
    public UUID nNoteId;
    private Date mDate;

    public MyNotes(){
        nNoteId = UUID.randomUUID();
        mDate = new Date();
    }

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
