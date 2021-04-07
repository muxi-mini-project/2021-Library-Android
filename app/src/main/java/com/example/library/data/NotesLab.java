package com.example.library.data;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesLab {
    private static NotesLab sNotesLab;
    private List<Notes> mNotes;
    private List<MyNotes> MyNotes;

    private NotesLab(Context context) {
        mNotes = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Notes notes = new Notes();
            notes.setNoteTitle("title #" + i);
            notes.setNoteContent(" I write note" + i);
            notes.setNoteWriter(" Ms." + i);
            notes.setCMContent("I think book" + i + "is good.");
            mNotes.add(notes);
        }

        MyNotes = new ArrayList<>();
        for (int j = 0; j < 100; j++) {
            com.example.library.data.MyNotes myNotes = new MyNotes();
            myNotes.setNoteTitle("书名" + j);
            myNotes.setNoteContent("评论是" + j);
            myNotes.setNoteWriter("作者是" + j);
            myNotes.setCMContent("我jio的很棒");
            MyNotes.add(myNotes);
        }
    }

    public static NotesLab get(Context context) {
        if (sNotesLab == null) {
            sNotesLab = new NotesLab(context);
        }
        return sNotesLab;
    }

    public List<Notes> getNotes() {
        return mNotes;
    }

    public Notes getNotes(UUID id) {
        for (Notes notes : mNotes) {
            if (notes.getNoteId().equals(id)) {
                return notes;
            }
        }
        return null;
    }

    public List<com.example.library.data.MyNotes> getMyNotes() {
        return MyNotes;
    }

    public com.example.library.data.MyNotes getMyNotes(UUID Id) {
        for (com.example.library.data.MyNotes myNotes : MyNotes) {
            if (myNotes.getNoteId().equals(Id)) {
                return myNotes;
            }
        }
        return null;
    }
}
