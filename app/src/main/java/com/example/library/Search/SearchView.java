package com.example.library.Search;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.library.R;

public class SearchView extends LinearLayout implements View.OnClickListener{
    //输入框
    private EditText etInput;
    //搜索按钮
    private Button btSearch;
    //上下文对象
    private Context mContext;
    //弹出列表
    private ListView mListView;
    //推荐adapter
    private ArrayAdapter<String> mHintAdapter;
    //自动补全adapter
    private ArrayAdapter<String> mAutoCompleteAdapter;
    //搜索回调接口
    private SearchViewListener mListener;

/*search view回调方法*/
    public interface SearchViewListener{
        //更新自动补全内容   传入补全后的文本
        void onRefreshAutoComplete(String text);
        //开始搜索   传入输入框的文本
        void onSearch(String text);
        //提示列表项点击时回调方法（提示/自动补全）
        //void onTipsItemClick(String text)
    }
/*设置搜索回调接口*/
    public void setSearchViewListener(SearchViewListener listener){
        mListener = listener;
    }

/*searchView构造器*/
    public SearchView(Context context, AttributeSet attrs){
        super(context,attrs);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.search_view,this);

        initViews();
    }

    private void initViews() {
        etInput = (EditText) findViewById(R.id.search_et_input);
        btSearch = (Button) findViewById(R.id.search_btn_back);
        mListView = (ListView) findViewById(R.id.search_lv_tips);
    /*弹出选择列表后。lv每一项设置监听器*/
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //点击每一项得到该位置的值
                String text = mListView.getAdapter().getItem(position).toString();
                //输入框补全
                etInput.setText(text);
                etInput.setSelection(text.length());
                //选择列表消失
                mListView.setVisibility(View.GONE);
                //结果出现
                notifyStartSearching(text);
            }
        });
    /*button*/
        btSearch.setOnClickListener((OnClickListener) this);
    /*editText。干啥的？？？*/
        etInput.addTextChangedListener(new EditChangedListener());
        etInput.setOnClickListener((OnClickListener) this);
        etInput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH){
                    mListView.setVisibility(GONE);
                    notifyStartSearching(etInput.getText().toString());
                }
                return true;
            }
        });
    }
/*通知监听者，进行搜索操作*/
    private void notifyStartSearching(String text){
        if (mListener != null){
            mListener.onSearch(etInput.getText().toString());
        }
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0,InputMethodManager.HIDE_NOT_ALWAYS);
    }
/*设置热搜榜提示adapter*/
    public void setTipsHintAdapter(ArrayAdapter<String> adapter){
        this.mHintAdapter = adapter;
        if (mListView.getAdapter() == null){
            mListView.setAdapter(mHintAdapter);
        }
    }
/*设置自动补全adapter*/
    public void setAutoCompleteAdapter(ArrayAdapter<String> adapter){
        this.mAutoCompleteAdapter = adapter;
    }
/*输入框监听类*/
    private class EditChangedListener implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //当输入框有文本时，点击输入框，应显示自动补全列表框
            if (!"".equals(s.toString())){
                mListView.setVisibility(VISIBLE);
                if (mAutoCompleteAdapter != null && mListView.getAdapter() != mAutoCompleteAdapter){
                    mListView.setAdapter(mAutoCompleteAdapter);
                }
                //输入框的文本发生改变时，需要更新自动补全列表框的数据.更新autoComplete数据
                if (mListener != null){
                    mListener.onRefreshAutoComplete(s + "");
                }
            }else {
                //当输入框没有文本时，点击输入框，显示热门搜索列表框
                if (mHintAdapter != null){
                    mListView.setAdapter(mHintAdapter);
                }
                mListView.setVisibility(GONE);
            }

        }
        @Override
        public void afterTextChanged(Editable s) {

        }
    }
/*点击searchView后发生的事件：点击搜索框，弹出lv；点击按钮，结束这个activity*/
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.search_et_input:
                mListView.setVisibility(VISIBLE);
                break;
            case R.id.search_btn_back:
                ((Activity) mContext).finish();
                break;
        }
    }
}
