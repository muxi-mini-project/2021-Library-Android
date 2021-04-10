package com.example.library.Searcher;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;
import com.example.library.data.GetDigest;
import com.example.library.fragment.ChoseBookExtract;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity2 extends AppCompatActivity implements SearchView2.SearchViewListener  {

    /**
     * 搜索结果列表view
     */
    private ListView lvResults;

    /**
     * 搜索view
     */
    private SearchView2 searchView;


    /**
     * 热搜框列表adapter
     */
    private ArrayAdapter<String> hintAdapter;

    /**
     * 自动补全列表adapter
     */
    private ArrayAdapter<String> autoCompleteAdapter;

    /**
     * 搜索结果列表adapter
     */
    private SearchAdapter2 resultAdapter;

    /**
     * 数据库数据，总数据
     */
    private List<GetDigest.DataDTO> dbData;
    private List<String> searchData;

    /**
     * 热搜版数据
     */
    private List<String> hintData;

    /**
     * 搜索过程中自动补全数据
     */
    private List<String> autoCompleteData;

    /**
     * 搜索结果的数据
     */
    private List<String> resultData1;

    /**
     * 默认提示框显示项的个数
     */
    private static int DEFAULT_HINT_SIZE = 4;

    /**
     * 提示框显示项的个数
     */
    private static int hintSize = DEFAULT_HINT_SIZE;

    /**
     * 设置提示框显示项的个数
     *
     * @param hintSize 提示框显示个数
     */
    public static void setHintSize(int hintSize) {
        SearchActivity2.hintSize = hintSize;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.searchview);
        initData();
        initViews();
//        getrequest();
    }

  /*  private void getrequest() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://124.71.184.107:10086/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
        Call<SearchView2> bookExtractCall = mApi.getpost("7");
        bookExtractCall.enqueue(new Callback<SearchView2>() {
            @Override
            public void onResponse(Call<SearchView2> call, Response<SearchView2> response) {
                Toast.makeText(SearchActivity2.this,"搜索完成",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SearchView2> call, Throwable t) {
                Toast.makeText(SearchActivity2.this,"无结果",Toast.LENGTH_SHORT).show();
            }
        });
    }*/

    /**
     * 初始化视图
     */
    private void initViews() {
        lvResults = (ListView) findViewById(R.id.main_lv_search_results);
        searchView = (SearchView2) findViewById(R.id.search_layout2);
        //设置监听
        searchView.setSearchViewListener(this);
        //设置adapter
        searchView.setTipsHintAdapter(hintAdapter);
        searchView.setAutoCompleteAdapter(autoCompleteAdapter);

        lvResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Toast.makeText(SearchActivity2.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //从数据库获取数据
        getDbData();
        //初始化热搜版数据
        getHintData();
        //初始化自动补全数据
        getAutoCompleteData(null);
        //初始化搜索结果数据
        getResultData(null);
    }

    /**
     * 获取db 数据
     */
    private void getDbData() {

        dbData = ChoseBookExtract.mBook_extract_list;
        searchData=new ArrayList<>();
        for (int i = 0; i < searchData.size(); i++) {
            searchData.add(dbData.get(i).getTitle());
        }
    }

    /**
     * 获取热搜版data 和adapter
     */
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        for (int i = 1; i <= hintSize; i++) {
            hintData.add("书摘" + i);
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
            for (int i = 0, count = 0; i < dbData.size()
                    && count < hintSize; i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    autoCompleteData.add(dbData.get(i).getTitle());
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
        if (resultData1 == null) {
            // 初始化
            resultData1 = new ArrayList<>();
        } else {
            resultData1.clear();
            for (int i = 0; i < dbData.size(); i++) {
                if (dbData.get(i).getTitle().contains(text.trim())) {
                    resultData1.add(searchData.get(i));
                }
            }
        }
        if (resultAdapter == null) {
            resultAdapter = new SearchAdapter2(this, resultData1, R.layout.searcher);
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
        lvResults.setVisibility(View.VISIBLE);
        //第一次获取结果 还未配置适配器
        if (lvResults.getAdapter() == null) {
            //获取搜索数据 设置适配器
            lvResults.setAdapter((ListAdapter) resultAdapter);
        } else {
            //更新搜索数据
            resultAdapter.notifyDataSetChanged();
        }
        Toast.makeText(this, "完成搜素", Toast.LENGTH_SHORT).show();
    }

}
