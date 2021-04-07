package com.example.library;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class edit_item<DeleteCallback> extends edit implements View.OnClickListener{
    private RecyclerView mBook_kind_recyclerview;
    private TextView mBook_name;
    private Button mDelete;
    ArrayList<String>data=new ArrayList<String>();
    int count;
    private MessageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_item);
        onitView();
    }

    private void onitView() {
        mBook_name=(TextView)findViewById(R.id.book_name);
       mDelete=(Button)findViewById(R.id.delete);
        mAdapter=new MessageAdapter(this);
        mAdapter.setData(data);
        mAdapter.setDeketeCallback(this);
        mDelete.setOnClickListener(this);

    }


   public void delete(View view) {
        

    }
}
