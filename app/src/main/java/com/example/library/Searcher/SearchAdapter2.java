package com.example.library.Searcher;

import android.content.Context;

import com.example.library.Search.BaseViewHolder;
import com.example.library.Search.CommonAdapter;
import com.example.library.Search.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter2 extends CommonAdapter<String> {

    private static final List<String>  resultData1 = new ArrayList<>();

    public SearchAdapter2(Context context, List<String> resultData1, int layoutId) {
        super(context, resultData1, layoutId);
    }

    @Override
    public void bindViewHolder(BaseViewHolder holder, String itemData, int position) {

    }
}
