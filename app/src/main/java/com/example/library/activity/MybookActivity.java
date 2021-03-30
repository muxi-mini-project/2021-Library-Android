package com.example.library.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
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

    public final static int F1 = 0xeff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mybook);

        textView1 = (TextView) findViewById(R.id.my_book_title);
        recyclerView = (RecyclerView) findViewById(R.id.my_book_recyclerView1);
        textView2 = (TextView) findViewById(R.id.my_book_change);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new GridLayoutManager(MybookActivity.this, 4);
        recyclerView.setLayoutManager(linearLayoutManager);
        BookLab bookLab = BookLab.get(MybookActivity.this);
        List<MyBook> mybook = bookLab.getmMyBooks();
        recyclerView.setAdapter(new MybookActivity.MybookAdapter(mybook, MybookActivity.this));


    }


    class MybookAdapter extends RecyclerView.Adapter<MybookActivity.MybookAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<MyBook> myBookList;


        public MybookAdapter(List<MyBook> list, Context context) {
            this.myBookList = list;
            this.context = context;
        }

        @Override
        public MybookActivity.MybookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(MybookActivity.this);
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            MyBook myBook = myBookList.get(position);
            holder.bind(myBook);
            holder.imageView.setOnClickListener(this);
            holder.imageView.setOnLongClickListener(this);
        }

        @Override
        public int getItemCount() {
            return myBookList.size();
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(MybookActivity.this, "单击", Toast.LENGTH_SHORT);
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MybookActivity.this, "长按", Toast.LENGTH_SHORT);
            return true;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView;


            public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
                super(inflate.inflate(R.layout.item_my_book, parent, false));
                imageView = itemView.findViewById(R.id.mybook_pic);
                textView = itemView.findViewById(R.id.mybook_name);


            }

            public void bind(MyBook myBook) {
                textView.setText(myBook.getBook_name());

            }
        }
    }
}
