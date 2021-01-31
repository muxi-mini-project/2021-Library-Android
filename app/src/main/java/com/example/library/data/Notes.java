package com.example.library.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Notes {
    public UUID mNoteId;
    public String mNoteTitle;
    public String mNoteContent;
    public String mNoteWriter;
    public Date mNoteDate;

    public Notes(){
        mNoteId = UUID.randomUUID();
        mNoteDate = new Date();//现在时间
    }

    public Notes(String noteTitle, String noteContent) {
        mNoteId = UUID.randomUUID();
        mNoteTitle = noteTitle;
        mNoteContent = noteContent;
        mNoteDate = new Date();

    }

    public Notes(String noteTitle, String noteContent, Date noteDate) {
    }

    public String getNoteWriter() {
        return mNoteWriter;
    }

    public void setNoteWriter(String noteWriter) {
        mNoteWriter = noteWriter;
    }

    public UUID getNoteId() {
        return mNoteId;
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

    public String getNoteDate() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd");
        String t=format.format(mNoteDate);
        return t;
    }

    public void setNoteDate(Date noteDate) {
        mNoteDate = noteDate;
    }
}
