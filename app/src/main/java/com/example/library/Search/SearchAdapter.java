package com.example.library.Search;

import android.content.Context;
import android.widget.TextView;

import com.example.library.R;
import com.example.library.Searcher.SearcherMain;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends CommonAdapter<String>{
    private static final List<String> resultData =new ArrayList<>();
    private List<String> mBookList;
    public SearchAdapter(Context context, int layoutId,List<String> data) {
        super(context, resultData, R.layout.item_bean_list);
        this.mBookList = data;
    }

    public SearchAdapter(SearcherMain context, List<String> resultData, int searcher) {
        super(context, resultData, searcher);
    }

    @Override
    public void bindViewHolder(BaseViewHolder holder, String itemData, int position) {
        TextView tv1 = holder.findViewById(R.id.item_search_tv_title);
        TextView tv2 = holder.findViewById(R.id.item_search_tv_content);
        TextView tv3 = holder.findViewById(R.id.item_search_tv_comments);
    }
}
