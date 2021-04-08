package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class edit_item_java extends AppCompatActivity {
    public List<String> kindlist;
    private String message;

    public List<String> getKindlist() {
        return kindlist;
    }

    public void setKindlist(List<String> kindlist) {
        this.kindlist = kindlist;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public java.lang.String toString() {
        return "Edit{" +
                "data=" + kindlist +
                ", message='" + message + '\'' +
                '}';
    }

    public String getBook(int id,List<String> data){
        for (String string:data){
            if (string.Id.equals(id)){
                return string;
            }
        }
        return null;
    }



    public static class String {
        public String kind;
        private UUID Id;

        public UUID getId() {
            return Id;
        }

        public void setId(UUID id) {
            Id = id;
        }

        public String(String kind) {
            this.kind = kind;
        }

        public String getKind() {
            return kind;
        }

        public void setKind(String kind) {
            this.kind = kind;
        }
    }
}
