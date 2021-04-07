package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

public class edit_item_java extends AppCompatActivity {
    public String kind;
    public edit_item_java(String kind){
        this.kind=kind;
    }
    public String getKind(){
        return kind;
    }

}
