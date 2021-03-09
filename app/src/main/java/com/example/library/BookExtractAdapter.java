package com.example.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookExtractAdapter extends RecyclerView.Adapter<BookExtractAdapter.ViewHolder> {

    private List<book_extract> mBook_extract;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookname;
        Button context;
        TextView date;

        public ViewHolder(View view) {
            super(view);
            bookname = (TextView) view.findViewById(R.id.book_extract_name);
            context = (Button) view.findViewById(R.id.book_extract_context);
            date = (TextView) view.findViewById(R.id.date);
        }

    }

    public BookExtractAdapter(List<book_extract> book_extractList) {
        mBook_extract = book_extractList;
    }

    @Override

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_extract_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        book_extract fruit = mBook_extract.get(position);
        holder.bookname.setText(fruit.getBookname());
        holder.context.setText(fruit.getContextId());
        holder.date.setText(fruit.getDateId());
    }
//hx增加以下方法，是否这样？
    @Override
    public int getItemCount() {
        return mBook_extract.size();
    }

}