package com.example.library.BookExtract;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.library.R;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.library.data.BookExtractLab;

import java.util.ArrayList;
import java.util.List;

//将数据与每一个条目的界面进行绑定
public class BookExtractAdapter extends RecyclerView.Adapter<BookExtractAdapter.ViewHolder> {
    public Context context;
    //防止空指针异常
    private List<BookExtract> mBook_extract_list = new ArrayList<>();
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public BookExtractAdapter(Context context, List<BookExtract> mBook_extract_list) {
        this.context = context;
        this.mBook_extract_list = mBook_extract_list;
    }

    public interface OnRecyclerViewItemClickListener {
        void onRecyclerViewItemClicked(int position);
    }

    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener clickListener) {
        this.mOnRecyclerViewItemClickListener = clickListener;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookname;
        TextView context;
        TextView date;
        BookExtractLab mBookextractLab;


        public ViewHolder(@NonNull View view, final OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
            super(view);
            bookname = (TextView) view.findViewById(R.id.book_extract_name);
            context = (TextView) view.findViewById(R.id.book_extract_context);
            date = (TextView) view.findViewById(R.id.date);
             /*view.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                   if(onRecyclerViewItemClickListener!=null){
                       int position=getBindingAdapterPosition();
                       //确保position的值有效
                       if(position!=RecyclerView.NO_POSITION){
                           onRecyclerViewItemClickListener.onItemClicked(view,position);
                       }
                   }
                 }
             });*/
        }
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_extract_item, parent, false);
        ViewHolder holder = new ViewHolder(view, mOnRecyclerViewItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //创建前面实体类对象
        BookExtract extract = mBook_extract_list.get(position);
        //将具体的值赋予子控件
        holder.bookname.setText(extract.getBook_extract_name());
        holder.date.setText(extract.getBook_extract_date());
        holder.context.setText(extract.getBook_extract_context());
        //设置条目中的点击监听
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRecyclerViewItemClickListener.onRecyclerViewItemClicked(position);
                Intent intent = new Intent(context, BookExtratDetail.class);
                intent.putExtra("书摘名称", extract.getBook_extract_name().toString());
                intent.putExtra("书摘内容", extract.getBook_extract_context().toString());
                intent.putExtra("日期", extract.getBook_extract_date().toString());
                context.startActivity(intent);
            }
        });
       /* holder.context.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sr=new ();

            }
        });?*/

    }

    //用以返回长度
    @Override
    public int getItemCount() {
        return mBook_extract_list.size();
    }
}

