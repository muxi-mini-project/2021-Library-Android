package com.example.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.data.BookLab;
import com.example.library.data.MyBook;

import java.util.List;

public class MineActivity extends AppCompatActivity {
    private ImageButton mImageButton_tou;
    private TextView name;
    private TextView zuoyouming;
    private TextView set;
    private TextView manager;
    private MyBookAdapt mAdapter;

    private LinearLayoutManager mLayoutManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_qyh);

        mImageButton_tou = (ImageButton) findViewById(R.id.tou_xiang);
        mImageButton_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*更换头像等功能*/
            }
        });

        /*待完善的功能*/
        name = (TextView) findViewById(R.id.Name);
        zuoyouming = (TextView) findViewById(R.id.zuoyou);
        set = (TextView) findViewById(R.id.shezhi);
        manager = (TextView) findViewById(R.id.manager);

        /*RecycleView部分*/

        RecyclerView mRecyclerView = (RecyclerView) this.findViewById(R.id.my_bookshelf_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        BookLab bookLab = BookLab.get(this);
        List<MyBook> mybook = bookLab.getmMyBooks();
        mRecyclerView.setAdapter(new MyBookAdapt(mybook,MineActivity.this) );


    }

    class MyBookAdapt extends RecyclerView.Adapter <MyBookAdapt.Holder> {

        private List<MyBook> mMyBook;
        private Context mContext;
        private LayoutInflater mLayoutInflater;

        public MyBookAdapt(List<MyBook> myBook,Context context){
            this.mMyBook = myBook;
            this.mContext = context;
        }
        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType){
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new Holder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position){
            MyBook myBook = mMyBook.get(position);
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            return mMyBook.size();
        }


        class Holder extends RecyclerView.ViewHolder{
            private ImageButton imageButton;
            private TextView textView;
            public Holder(LayoutInflater inflater,ViewGroup parent){
                super(inflater.inflate(R.layout.item_my_book,parent,false));

                imageButton = (ImageButton)itemView.findViewById(R.id.my_book);
                textView = (TextView)itemView.findViewById(R.id.my_book_name);
            }

            public void bind(MyBook myBook){

                textView.setText(myBook.getBookTitle());
            }

        }


    }



}



