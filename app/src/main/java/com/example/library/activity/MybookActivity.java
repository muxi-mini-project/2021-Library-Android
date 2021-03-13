package com.example.library.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.library.R;

public class MybookActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        textView1 = (TextView)findViewById(R.id.my_book_title);
        textView2 = (TextView)findViewById(R.id.my_book_manager);
        recyclerView = (RecyclerView) findViewById(R.id.my_book_recyclerview);
    }
}