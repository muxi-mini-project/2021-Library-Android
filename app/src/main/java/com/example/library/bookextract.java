package com.example.library;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.data.Book;

import java.util.ArrayList;
import java.util.List;

public class bookextract extends AppCompatActivity {
    private List<book_extract> mBook_extractList = new ArrayList<>();
    private Object book_extract;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_zhai);
        recyclerView = (RecyclerView) findViewById(R.id.book_extract_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        BookExtractAdapter adapter = new BookExtractAdapter(mBook_extractList);
        recyclerView.setAdapter(adapter);
    }


}
