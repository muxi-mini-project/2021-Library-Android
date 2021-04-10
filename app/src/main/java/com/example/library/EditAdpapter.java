package com.example.library;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.Interface.BookExtractInterface;
import com.example.library.activity.LoginActivity;
import com.example.library.data.GetDigest;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditAdpapter extends RecyclerView.Adapter<EditAdpapter.ViewHolder> {

    private static final String TAG ="EditAdapter" ;
    public Context context;
    public List<String> kindlist;
    private TextView mBook_name;
    private EditText mAdd_text;
    private EditData mEditData;
    String message;

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
                removeData(position);
                getrequest();
            }

            private void getrequest() {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://39.102.42.156:10086")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);

                Call delete = mApi.getDelete("0");
                delete.enqueue(new Callback() {
                    @Override
                    public void onResponse(Call call, Response response) {
                        if (response.code() == HttpURLConnection.HTTP_OK){
                            Toast.makeText(context,"删除成功",Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(context,"删除失败",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call call, Throwable t) {
                        Toast.makeText(context,"无网络链接",Toast.LENGTH_SHORT).show();
                    }
                });
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

    //删除数据
    public void removeData(int position){
        kindlist.remove(position);
        //删除动画
        notifyItemRemoved(position);
        notifyDataSetChanged();
    }
}
