package com.example.library.Search;

import android.content.Context;
import android.widget.TextView;

import com.example.library.R;
import java.util.List;

public class SearchAdapter extends CommonAdapter<String>{
    private List<String> mBookList;
    public SearchAdapter(Context context, int layoutId,List<String> data) {
        super(context, R.layout.item_bean_list);
        this.mBookList = data;
    }
    @Override
    public void bindViewHolder(BaseViewHolder holder, String itemData, int position) {
        TextView tv1 = holder.findViewById(R.id.item_search_tv_title);
        TextView tv2 = holder.findViewById(R.id.item_search_tv_content);
        TextView tv3 = holder.findViewById(R.id.item_search_tv_comments);
    }
}
