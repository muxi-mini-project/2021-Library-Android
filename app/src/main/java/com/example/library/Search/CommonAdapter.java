package com.example.library.Search;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected Context mContext;
    protected List<T> dataList = new ArrayList<>();
    protected int layoutId;
    private BaseViewHolder.OnItemClickListener itemClickListener;

    public CommonAdapter(Context context,int layoutId){
        this.mContext = context;
        this.layoutId = layoutId;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(mContext).inflate(layoutId,parent,false);
        return new BaseViewHolder(mContext,itemView);
    }
    @Override
    public void onBindViewHolder(final BaseViewHolder holder,int position){
        bindViewHolder(holder,dataList.get(position),position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.onItemClick(dataList.get(position),position);
                }
            }
        });
    }
    @Override
    public int getItemCount(){
        return dataList == null ? 0 : dataList.size();
    }

    public abstract void bindViewHolder(BaseViewHolder holder, T itemData, int position);

    public Context getContext(){
        return mContext;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public void setDataList(List<T> dataList){
        this.dataList = dataList;
        notifyDataSetChanged();
    }

}
