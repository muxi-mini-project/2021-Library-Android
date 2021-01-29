package com.example.library.data;

import java.util.Date;

public class Notes {
    public String mNoteTitle;
    public String mNoteContent;
    public Date mNoteDate;

    public Notes(String noteTitle, String noteContent, Date noteDate) {
        mNoteTitle = noteTitle;
        mNoteContent = noteContent;
        mNoteDate = noteDate;
    }

    public String getNoteTitle() {
        return mNoteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        mNoteTitle = noteTitle;
    }

    public String getNoteContent() {
        return mNoteContent;
    }

    public void setNoteContent(String noteContent) {
        mNoteContent = noteContent;
    }

    public Date getNoteDate() {
        return mNoteDate;
    }

    public void setNoteDate(Date noteDate) {
        mNoteDate = noteDate;
    }
}
