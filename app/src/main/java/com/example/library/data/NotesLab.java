package com.example.library.data;

import android.content.Context;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NotesLab {
    private static NotesLab sNotesLab;
    private List<Notes> mNotes;

    private NotesLab(Context context){
        mNotes = new ArrayList<>();
        for (int i = 0; i < 100; i++){
           Notes notes = new Notes();
           notes.setNoteTitle("title #" + i);
           notes.setNoteContent(" I write note" + i);
           notes.setNoteWriter(" Ms." + i);
           notes.setCMContent("I think book" + i + "is good.");
           mNotes.add(notes);
        }
    }

    public static NotesLab get(Context context) {
        if (sNotesLab == null) {
            sNotesLab = new NotesLab(context);
        }
        return sNotesLab;
    }

    public List<Notes> getNotes(){
        return mNotes;
    }

    public Notes getNotes(UUID id){
        for (Notes notes:mNotes){
            if (notes.getNoteId().equals(id)){
                return notes;
            }
        }
        return null;
    }
}
