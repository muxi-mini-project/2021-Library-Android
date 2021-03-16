package com.example.library;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BookExtractAdapter extends RecyclerView.Adapter<BookExtractAdapter.ViewHolder> {
    public Context context;
    //防止空指针异常
    private List<BookExtract> mBook_extract_list=new ArrayList<>();
    private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;

    public interface OnRecyclerViewItemClickListener{
        void onItemClicked(View view,int position);
    }
    public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener clickListener){
        this.mOnRecyclerViewItemClickListener=clickListener;
    }

    public BookExtractAdapter(Context context,List<BookExtract> mBook_extract_list) {
        this.context=context;
        this.mBook_extract_list=mBook_extract_list;
    }

     public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookname;
        Button context;
        TextView date;
        BookExtract mBookextractLab;

         public ViewHolder(@NonNull View view,final OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
             super(view);
             bookname = (TextView) view.findViewById(R.id.book_extract_name);
             context = (Button) view.findViewById(R.id.book_extract_context);
             date = (TextView) view.findViewById(R.id.date);
             view.setOnClickListener(new View.OnClickListener() {
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
             });
         }
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_extract_item, parent, false);
        ViewHolder holder = new ViewHolder(view,mOnRecyclerViewItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //创建前面实体类对象
        BookExtract extract = mBook_extract_list.get(position);
        //将具体的值赋予子控件
        holder.bookname.setText(extract.getBook_extract_name());
        holder.context.setText(extract.getBook_extract_context());
        holder.date.setText(extract.getBook_extract_date());

    }
   //用以返回长度
    @Override
    public int getItemCount() {
        return mBook_extract_list.size();
    }

}