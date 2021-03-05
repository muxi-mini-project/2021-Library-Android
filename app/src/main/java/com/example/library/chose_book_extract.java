package com.example.library;


import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;

import java.nio.file.WatchEvent;
import java.util.ArrayList;
import java.util.List;

import kotlin.reflect.KParameter;

public class chose_book_extract extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner mChose;
    private Button mEdit;
    private Button mAdd;
    private TextView mBook_extract;
    private EditText mBook_search;
    private List<String> list = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_zhai);
        list.add("历史");
        list.add("文学");
        list.add("诗歌");
        SpinnerAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        mChose.setAdapter(adapter);
        mChose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
        public void onItemSelected(AdapterView<?> arg0, View arg1,
                                   int arg2, long arg3) {
            mBook_extract.setText((Integer) adapter.getItem(arg2));
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
         mBook_extract.setText("全部书摘");
        }
    });
        initView();
        bindView();
    }

    private void bindView() {
        mChose=(Spinner)findViewById(R.id.chose);
        mChose.setOnItemSelectedListener(this);

    }

    private void initView() {
        mAdd=(Button)findViewById(R.id.add);
        mBook_extract=(TextView)findViewById(R.id.book_extract);
        mBook_search=(EditText) findViewById(R.id.book_search);
        mEdit=(Button)findViewById(R.id.edit);
        mAdd.setOnClickListener((View.OnClickListener) this);
        mEdit.setOnClickListener((View.OnClickListener) this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //@Override
   // public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
      //  mBook_extract
    //}

    //@Override
    //public void onNothingSelected(AdapterView<?> parent) {

    //}
}
