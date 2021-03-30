package com.example.library.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.data.BookLab;
import com.example.library.data.CommentDetail;
import com.example.library.data.CommentLab;
import com.example.library.data.MyBook;

import org.w3c.dom.Comment;

import java.util.List;

public class MyDigestActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private RecyclerView recyclerView;
    private Context context;
    private LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypublicaion);

        textView1 = (TextView) findViewById(R.id.mydigest_title);
        recyclerView = (RecyclerView) findViewById(R.id.mydigest_recyclerView);
        textView2 = (TextView) findViewById(R.id.mydigest_change);
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        CommentLab commentLab = CommentLab.get(MyDigestActivity.this);
        List<CommentDetail> commentDetails = commentLab.getCommentDetailList();
        recyclerView.setAdapter(new MyDigestActivity.MybookAdapter(commentDetails, MyDigestActivity.this));


    }


    class MybookAdapter extends RecyclerView.Adapter<MyDigestActivity.MybookAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {
        private Context context;
        private RecyclerView.OnItemTouchListener listener1;
        private AdapterView.OnItemClickListener listener;
        private List<CommentDetail> CommentDetail;


        public MybookAdapter(List<CommentDetail> list, Context context) {
            this.CommentDetail = list;
            this.context = context;
        }

        @Override
        public MyDigestActivity.MybookAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflate = LayoutInflater.from(MyDigestActivity.this);
            return new ViewHolder(inflate, parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            CommentDetail commentDetail =CommentDetail.get(position);
            holder.bind(commentDetail);
            holder.imageView.setOnClickListener(this);
            holder.imageView.setOnLongClickListener(this);
        }

        @Override
        public int getItemCount() {
            return CommentDetail.size();
        }

        @Override
        public void onClick(View v) {
            Toast.makeText(MyDigestActivity.this, "单击", Toast.LENGTH_SHORT);
        }

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MyDigestActivity.this, "长按", Toast.LENGTH_SHORT);
            return true;
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imageView;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;


            public ViewHolder(LayoutInflater inflate, ViewGroup parent) {
                super(inflate.inflate(R.layout.item_my_publication, parent, false));
                imageView = itemView.findViewById(R.id.mybook_pic);
                textView1 = itemView.findViewById(R.id.mybook_name);



            }

            public void bind(CommentDetail commentDetail) {
                textView1.setText(commentDetail.getCommentName());

            }
        }
    }
}
