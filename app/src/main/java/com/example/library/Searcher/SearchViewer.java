package com.example.library.Searcher;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.library.R;

public class SearchViewer extends LinearLayout implements View.OnClickListener {

    private ImageView mImage_search;
    private EditText mBook_search;
    private ImageView mSearch_delete;
    private Context mContext;
    // 弹出列表
    private ListView mLvTips;

    // 提示adapter （推荐adapter）
    private ArrayAdapter<String> mHintAdapter;

    // 自动补全adapter 只显示名字
    private ArrayAdapter<String> mAutoCompleteAdapter;

     // 搜索回调接口
    private SearchViewListener mListener;


      //设置搜索回调接口
      //@param listener 监听者
    public void setSearchViewListener(SearchViewListener listener) {
        mListener = listener;
    }

    public SearchViewer(Context context) {

        super(context);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.book_zhai, this);
        initViews();
    }

    private void initViews() {
        mImage_search=(ImageView) findViewById(R.id.image_search);
        mBook_search=(EditText)findViewById(R.id.book_search);
        mSearch_delete=(ImageView)findViewById(R.id.search_delete);
        mLvTips=(ListView)findViewById(R.id.search_lv_tips);

        mLvTips.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //设置  edit text
                String text = mLvTips.getAdapter().getItem(i).toString();
                mBook_search.setText(text);
                mBook_search.setSelection(text.length());
                //hint list view gone and result list view show
                mLvTips.setVisibility(View.GONE);
                notifyStartSearching(text);
            }
        });
        mSearch_delete.setOnClickListener(this);
        mBook_search.addTextChangedListener(new EditChangedListener());
        mBook_search.setOnClickListener(this);
        mBook_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    mLvTips.setVisibility(GONE);
                    notifyStartSearching(mBook_search.getText().toString());
                }
                return true;
            }
        });
    }

     //通知监听者 进行搜索操作
     // @param text

    private void notifyStartSearching(String text){
        if (mListener != null) {
            mListener.onSearch(mBook_search.getText().toString());
        }
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }


     // 设置热搜版提示 adapter
    public void setTipsHintAdapter(ArrayAdapter<String> adapter) {
        this.mHintAdapter = adapter;
        if (mLvTips.getAdapter() == null) {
            mLvTips.setAdapter(mHintAdapter);
        }
    }


     //设置自动补全adapter
    public void setAutoCompleteAdapter(ArrayAdapter<String> adapter) {
        this.mAutoCompleteAdapter = adapter;
    }

    @Override
    public void onClick(View v) {

    }

    private class EditChangedListener implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            if (!"".equals(charSequence.toString())) {
                mSearch_delete.setVisibility(VISIBLE);
                mLvTips.setVisibility(VISIBLE);
                if (mAutoCompleteAdapter != null && mLvTips.getAdapter() != mAutoCompleteAdapter) {
                    mLvTips.setAdapter(mAutoCompleteAdapter);
                }
                //更新autoComplete数据
                if (mListener != null) {
                    mListener.onRefreshAutoComplete(charSequence + "");
                }
            } else {
                mSearch_delete.setVisibility(GONE);
                if (mHintAdapter != null) {
                    mLvTips.setAdapter(mHintAdapter);
                }
                mLvTips.setVisibility(GONE);
            }

        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

   /* @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_et_input:
                mLvTips.setVisibility(VISIBLE);
                break;
            case R.id.search_iv_delete:
                mBook_search.setText("");
                mSearch_delete.setVisibility(GONE);
                break;
            case R.id.search_btn_back:
                ((Activity) mContext).finish();
                break;
        }
    }*/

    /**
     * search view回调方法
     */
    public interface SearchViewListener {
         //更新自动补全内容
         //@param text 传入补全后的文本
        void onRefreshAutoComplete(String text);

         // 开始搜索
         // @param text 传入输入框的文本
        void onSearch(String text);

//
//         提示列表项点击时回调方法 (提示/自动补全)
//
//        void onTipsItemClick(String text);
    }

}




