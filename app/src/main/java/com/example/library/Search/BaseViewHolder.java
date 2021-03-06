package com.example.library.Search;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.AdapterView;

import androidx.recyclerview.widget.RecyclerView;

public class BaseViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;//轻量级存储数据
    private Context mContext;
    public View itemView;
    private AdapterView.OnItemClickListener mItemClickListener;

    //需要我们提供一个 itemView 作为每一个 item 的视图，
// 然后在里面通过这个 itemView 的 findViewById() 方法来找到里面的子控件，用于我们设置数据
    public BaseViewHolder(Context context, View itemView) {
        super(itemView);
        this.mContext = context;
        this.itemView = itemView;
        this.mViews = new SparseArray<>();
    }
    /*
     * SparseArray 是 Android 特有的容器，相当于 key 为 int 型的 Map，
     * 控件的 id 正好是 int 型的，所以用这个容器来存放子控件，
     * 当调用 findViewById() 时，如果 SparseArray 中有则直接取出来返回，
     * 如果没有再调用 itemView 的 findViewById() 方法找到该控件返回，并存到容器中。
     * */
    public <T extends View > T findViewById( int viewId){
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }
    /*点击事件接口*/
    public interface OnItemClickListener {
         <T> void onItemClick( T data, int position);
    }
    /*设置点击事件的方法*/
    public void setItemClickListener (OnItemClickListener itemClickListener){
        this.mItemClickListener = (AdapterView.OnItemClickListener) itemClickListener;
    }

    public View getItemView() {
        return itemView;
    }
}