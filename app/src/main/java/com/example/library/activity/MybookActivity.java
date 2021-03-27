package com.example.library.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.R;
import com.example.library.data.BookLab;
import com.example.library.data.MyBook;
import com.example.library.fragment.sonfragment.RecommendFragment;

import java.util.List;

public class MybookActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private RecyclerView recyclerView;
    private Context context;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        textView1 = (TextView) findViewById(R.id.my_book_title);
        recyclerView = (RecyclerView) findViewById(R.id.my_book_recyclerView1);
        textView2 = (TextView) findViewById(R.id.my_book_change);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MenuShow(textView2);
            }
        });

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        BookLab bookLab = BookLab.get(MybookActivity.this);
        List<MyBook> mybook = bookLab.getmMyBooks();
        recyclerView.setAdapter(new MybookActivity.MybookAdapter(mybook,MybookActivity.this));


    }

    /*显示弹出菜单的方法*/
    public void MenuShow(View view) {
        PopupMenu popupMenu = new PopupMenu(context, view);
        popupMenu.getMenuInflater().inflate(R.menu.list, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.list_delete:
                        Toast.makeText(context, "删除方法", Toast.LENGTH_SHORT);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    class MybookAdapter extends RecyclerView.Adapter<MybookActivity.MybookAdapter.ViewHolder> {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<MyBook> myBookList;


        public MybookAdapter(List<MyBook> list,Context context) {
            this.myBookList = list;
            this.context = context;
        }

        @Override
        public MybookActivity.MybookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.each_my_book, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyBook myBook = myBookList.get(position);
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            return myBookList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView;

            public ViewHolder(View v) {
                super(v);
                imageView = v.findViewById(R.id.mybook_pic);
                textView = v.findViewById(R.id.mybook_name);
            }

            public void bind(MyBook myBook){
                textView.setText(myBook.getBookTitle());
            }
        }

    }


}