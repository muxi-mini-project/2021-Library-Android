package com.example.library.Searcher;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.Search.SearchAdapter;
import com.example.library.activity.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class SearcherMain extends AppCompatActivity implements SearchViewer.SearchViewListener {

    // 搜索结果列表view
    //private ListView lvResults;

     //搜索view
    private SearchViewer searchView;

     // 热搜框列表adapter
    private ArrayAdapter<String> hintAdapter;

     // 自动补全列表adapter
    private ArrayAdapter<String> autoCompleteAdapter;

     // 搜索结果列表adapter
    private SearchAdapter resultAdapter;

     // 数据库数据，总数据
     List<String> bookExtractData = new ArrayList<>();

     //热搜版数据
    private List<String> hintData;

     // 搜索过程中自动补全数据
    private List<String> autoCompleteData;

     // 搜索结果的数据
     private List<String> resultData;

     // 默认提示框显示项的个数
    private static int DEFAULT_HINT_SIZE = 4;

     // 提示框显示项的个数
    private static int hintSize = DEFAULT_HINT_SIZE;

     // 设置提示框显示项的个数
    //@param hintSize 提示框显示个数
    public static void setHintSize(int hintSize) {
        SearcherMain.hintSize = hintSize;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searcher);
        initData();
        initViews();
    }

    //初始化视图
    private void initViews() {
        //lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchViewer) findViewById(R.id.searcher_layout);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);

    }

    //初始化数据
    private void initData() {
        //从数据库获取数据
        getBookExtractData();
        //初始化热搜版数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    //获取data数据
    private void getBookExtractData() {
        int size = 100;
        bookExtractData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            //bookExtractData.add(new Bean(R.drawable.icon, "android开发必备技能" + (i + 1), "Android自定义view——自定义搜索view", i * 20 + 2 + ""));
        }
    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        for (int i = 1; i <= hintSize; i++) {
            hintData.add("热搜版" + i + "：Android自定义View");
        }
        hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, hintData);
    }

    /**
     * 获取自动补全data 和adapter
     */
    private void getAutoCompleteData(String text) {
        if (autoCompleteData == null) {
            //初始化
            autoCompleteData = new ArrayList<>(hintSize);
        } else {
            // 根据text 获取auto data
            autoCompleteData.clear();
            for (int i = 0, count = 0; i < bookExtractData.size()
                    && count < hintSize; i++) {
                if (bookExtractData.get(i).contains(text.trim())) {
                    autoCompleteData.add(bookExtractData.get(i));
                    count++;
                }
            }
        }
        if (autoCompleteAdapter == null) {
            autoCompleteAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, autoCompleteData);
        } else {
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 获取搜索结果data和adapter
     */
    private void getResultData(String text) {
        if (resultData == null) {
            // 初始化
            resultData = new ArrayList<>();
        } else {
            resultData.clear();
            for (int i = 0; i < bookExtractData.size(); i++) {
                if (bookExtractData.get(i).contains(text.trim())) {
                    resultData.add(bookExtractData.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter(this, resultData, R.layout.searcher);
        } else {
            resultAdapter.notifyDataSetChanged();
        }
    }

    /**
     * 当搜索框 文本改变时 触发的回调 ,更新自动补全数据
     * @param text
     */
    @Override
    public void onRefreshAutoComplete(String text) {
        //更新数据
        getAutoCompleteData(text);
    }

    /**
     * 点击搜索键时edit text触发的回调
     *
     * @param text
     */
    @Override
    public void onSearch(String text) {
        //更新result数据
        getResultData(text);
       // lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
       // if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            //lvResults.setAdapter(resultAdapter);
       // } else {
            //更新搜索数据
         //   resultAdapter.notifyDataSetChanged();
     //   }
        Toast.makeText(this, "完成搜素", Toast.LENGTH_SHORT).show();
    }



}
