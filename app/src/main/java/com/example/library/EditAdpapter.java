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

public class EditAdpapter extends RecyclerView.Adapter<EditAdpapter.ViewHolder> {

    public Context context;
    public List<String> kindlist=new ArrayList<String>();

    public EditAdpapter(Context context,List<String> kindlist){
        this.context=context;
        this.kindlist=kindlist;
    }

    @NonNull
    @Override
    public EditAdpapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.edit_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EditAdpapter.ViewHolder holder, int position) {
        holder.book_name.setText(kindlist.get(position));
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return kindlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView book_name;
        private Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            book_name=itemView.findViewById(R.id.book_name);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
