package com.example.library.Search;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.activity.BookDetailPagerActivity;
import com.example.library.data.BookData;
import com.example.library.data.DataBean;
import com.example.library.data.BookLab;
import com.example.library.fragment.sonfragment.RecommendFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchActivity extends AppCompatActivity implements SearchView.SearchViewListener {
    private static final String TAG = "SearchActivity";
    //搜索结果列表
    //private RecyclerView RvResult;
    //搜索view
    private SearchView searchView;
    //热搜框adapter
    private ArrayAdapter<String> hintAdapter;
    //自动补全列表adapter
    private ArrayAdapter<String> autoCompleteAdapter;
    //搜索结果列表adapter
    private SearchAdapter resultAdapter;
    //数据库数据，总数据
    List<String> nameList = new ArrayList<>();
    //热搜版数据
    private List<String> hintData;
    //自动补全数据
    private List<String> autoCompleteData;
    //结果数据
    private List<String> resultData;
    private List<BookData.DataBean> bookData;
    //默认提示框显示项个数
    private static int DEFAULT_HINT_SIZE = 4;
    //提示框显示项个数
    private static int hintSize = DEFAULT_HINT_SIZE;

/*设置提示框显示项的个数*/
    public static void setHintSize(int hintSize){
        SearchActivity.hintSize = hintSize;
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//???
        setContentView(R.layout.ac_search);
        initData();
        initViews();
    }

/*初始化视图*/
    private void initViews(){
        //RvResult = (RecyclerView) findViewById(R.id.ac_search_lv);
        searchView = (SearchView) findViewById(R.id.search_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);
    }
/*初始化数据*/
    private void initData(){
        //从数据库获取数据
        getBookData();
        //初始化热搜榜数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    private void getBookData() {
        //BookLab bookLab = BookLab.get(this);
        //bookData = bookLab.getBooks();
        bookData = RecommendFragment.data;
        nameList = new ArrayList<>();
        for (int j = 0 ; j < bookData.size();j++){
            nameList.add(bookData.get(j).getBook_name());
        }
    }
/*获取搜索结果data和adapter:可暂时忽略*/
    private void getResultData(String text) {
        if (resultData == null){
            resultData = new ArrayList<>();
        }else {
            resultData.clear();
            for (int i = 0; i < nameList.size(); i++){
                if (nameList.get(i).contains(text.trim())){
                    resultData.add(nameList.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter(this,R.layout.item_bean_list,resultData);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }
/*获取自动补全data和adapter*/
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null){
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        }else {
            //根据text获取有关键字的数据组成选择列表
            autoCompleteData.clear();
            for (int i = 0,count = 0; i < nameList.size() && count < hintSize; i++){
                if (nameList.get(i).contains(text.trim())){
                    autoCompleteData.add(nameList.get(i));
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null){
            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,autoCompleteData);
        }else{
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }
/*获取热搜版data和adapter*/
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        for (int i = 1; i <= hintSize; i++){
            hintData.add("热搜" + i );
        }
        hintAdapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1,hintData);
    }
/*当搜索框 文本改变时 触发的回调 ,更新自动补全数据*/
    public void onRefreshAutoComplete(String text){
        getAutoCompleteData(text);
    }
/*点击搜索键时edit text触发的回调*/
    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);
        /*RvResult.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (RvResult.getAdapter() == null) {
            //获取搜索数据 设置适配器
            RvResult.setAdapter(resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }*/
        int id = 1;
        for ( int j = 0; j < nameList.size(); j++){
            if (text.equals(nameList.get(j))){
                 id = bookData.get(j).getBook_id();
            }
        }
        if (id != 1){
            Intent intent = BookDetailPagerActivity.newIntent(this, id);
            startActivity(intent);
            Log.e(TAG,"text is ~~~~~~" + text +"~~~~~" + "intent is " + intent.toString());
            Toast.makeText(this, "完成搜suo", Toast.LENGTH_SHORT).show();
            }else {
            Toast.makeText(this, "不在搜索范围内", Toast.LENGTH_SHORT).show();
        }
    }
}
