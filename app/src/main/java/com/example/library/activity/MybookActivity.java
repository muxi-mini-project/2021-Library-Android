package com.example.library.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.R;
import com.example.library.data.Book;
import com.example.library.data.BookLab;
import com.example.library.data.MyBook;
import com.example.library.fragment.minefragment.mineFragment1;

import java.util.List;

public class MybookActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private MyBookAdapt myBookAdapt;
    private RecyclerView recyclerView;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        textView1 = (TextView)findViewById(R.id.my_book_title);
        recyclerView = (RecyclerView) findViewById(R.id.my_book_recyclerView);
        textView2 = (TextView)findViewById(R.id.my_book_change);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuShow(textView2);
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        BookLab bookLab = BookLab.get(context);
        List<MyBook> myBooks = bookLab.getmMyBooks();
        myBookAdapt = new MyBookAdapt(myBooks,this);


    }
    /*显示弹出菜单的方法*/
    public void MenuShow(View view){
        PopupMenu popupMenu = new PopupMenu(context,view);
        popupMenu.getMenuInflater().inflate(R.menu.list,popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.list_delete:
                        Toast.makeText(context,"删除方法",Toast.LENGTH_SHORT);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    class MyBookAdapt extends RecyclerView.Adapter<MybookActivity.MyBookAdapt.Holder> {

        private List<MyBook> mMyBook;
        private Context mContext;

        public MyBookAdapt(List<MyBook> myBook, Context context) {
            this.mMyBook = myBook;
            this.mContext = context;
        }

        @Override
        public MybookActivity.MyBookAdapt.Holder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new MyBookAdapt.Holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(MyBookAdapt.Holder holder, int position) {
            MyBook myBook = mMyBook.get(position);
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            return mMyBook.size();
        }


        class Holder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;

            public Holder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.each_my_book, parent, false));

                imageView = (ImageView) itemView.findViewById(R.id.my_book_pic);
                textView1 = (TextView) itemView.findViewById(R.id.my_book_title);
                textView2 = (TextView) itemView.findViewById(R.id.my_book_writer);
                textView3 = (TextView) itemView.findViewById(R.id.my_book_intro);
            }

            public void bind(MyBook myBook) {
                textView1.setText(myBook.getBookTitle());
                textView2.setText(myBook.getBookWriter());
                textView3.setText(myBook.getIntroduction());
            }

        }
    }
}