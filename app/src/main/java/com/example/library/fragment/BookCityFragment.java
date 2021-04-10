package com.example.library.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.library.Interface.BookService;
import com.example.library.R;
import com.example.library.Search.SearchAdapter;
import com.example.library.Search.SearchView;
import com.example.library.activity.BookDetailPagerActivity;
import com.example.library.activity.GuideActivity;
import com.example.library.data.BookData;
import com.example.library.data.BookLab;
import com.example.library.data.PostSearch;
import com.example.library.fragment.sonfragment.RankFragment;
import com.example.library.fragment.sonfragment.RecommendFragment;
import com.example.library.fragment.sonfragment.SortFragment;
import com.google.android.material.tabs.TabLayout;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BookCityFragment extends Fragment implements SearchView.SearchViewListener{
    private static final String TAG = "BookCityFragment";
    public TextView mEditText;
    private ArrayAdapter<String> mArrayAdapter;
    TabLayout mTableLayout;
    public ViewPager mViewPager;
    public MyAdapter adapter;
    private List<String> titles;//推荐/分类/排行
    private List<Fragment> mFragments;

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
    private List<BookData.DataBean> bookData = new ArrayList<>();

    private List<String> resultData;
    //默认提示框显示项个数
    private static int DEFAULT_HINT_SIZE = 4;
    //提示框显示项个数
    private static int hintSize = DEFAULT_HINT_SIZE;

    /*设置提示框显示项的个数*/
    public static void setHintSize(int hintSize){
        BookCityFragment.hintSize = hintSize;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.father_fg_bookcity,container,false);
        //实例化组件
        searchView = (SearchView) v.findViewById(R.id.search_layout);
        mEditText = (TextView) v.findViewById(R.id.book_city_edit_text);
        mTableLayout = v.findViewById(R.id.table_layout_city);
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);

        //初始化子fragment并组成数组
        mFragments = new ArrayList<>();
        mFragments.add(new RecommendFragment());
        mFragments.add(new SortFragment());
        mFragments.add(new RankFragment());
        //标题栏数组
        titles = new ArrayList<>();
        titles.add("推荐");
        titles.add("分类");
        titles.add("排行");

        init();
        getRequest();
        //initViews();
        return v;
    }

    //关联tableLayout和adapter
    private void init(){
        adapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTableLayout.setupWithViewPager(mViewPager);

    }
    /*初始化视图*/
    private void initViews(){
        //设置监听???
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

    public void getRequest(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookService mApi = retrofit.create(BookService.class);
        Call<BookData> bookDataCall = mApi.getCall();

        bookDataCall.enqueue(new Callback<BookData>() {
            //请求成功时回调
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                Log.e(TAG,"搜索的onResponse>>>>>" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK){
                    Log.d(TAG,"Json>>>>>" + response.body().toString());
                    bookData = response.body().getData();
                    Log.d(TAG,"data--------------" + bookData.toString());
                    initData();
                    initViews();
                    if (nameList.size()!=0){
                        Log.e(TAG,">>>>>>>>>>>>>>" + nameList.get(0) + "<<<<<<<<<<<");

                    }else{
                        Log.e(TAG,"it is null!!!" );
                    }
                }
            }

            //请求失败时回调
            @Override
            public void onFailure(Call<BookData> call, Throwable t)
            {
                Log.d(TAG,"error ---");
            }
        });
    }

    private void getBookData() {
        nameList = new ArrayList<>();
        for (int j = 0 ; j < bookData.size();j++){
            nameList.add(bookData.get(j).getBook_name());
        }
        if (nameList.size() != 0){
            Log.d(TAG,"是否有nameList的数据"+nameList.get(0));
        }else {
            Log.e(TAG,"No nameList!!!!");
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
            resultAdapter = new SearchAdapter(getActivity(),R.layout.item_bean_list,resultData);
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
            autoCompleteAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,autoCompleteData);
        }else{
            autoCompleteAdapter.notifyDataSetChanged();
        }
    }
    /*获取热搜版data和adapter*/
    private void getHintData() {
        hintData = new ArrayList<>(hintSize);
        for (int i = 16; i <= 19; i++){
            hintData.add(nameList.get(i));
        }
        hintAdapter = new ArrayAdapter<>
                (getActivity(), android.R.layout.simple_list_item_1,hintData);
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
        int id = 0;
        for ( int j = 0; j < nameList.size(); j++){
            if (text.equals(nameList.get(j))){
                id = bookData.get(j).getBook_id();
            }
        }
        if (id != 0){
            Intent intent = BookDetailPagerActivity.newIntent(getActivity(), id);
            startActivity(intent);
            //Log.e(TAG,"text is ~~~~~~" + text +"~~~~~" + "intent is " + intent.toString());
            Toast.makeText(getActivity(), "完成搜索", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getActivity(), "查询无结果", Toast.LENGTH_SHORT).show();
        }
    }

    //创建适配器
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm){
            super(fm);
        }
        //获得每个页面的下标
        @Override
        public Fragment getItem(int position){
            Log.d(TAG,"now is"+mFragments.get(position).toString()+"here....");
            return mFragments.get(position);
        }
        //获得List的大小
        @Override
        public int getCount(){
            return mFragments.size();
        }
        //获得title的下标
        @Override
        public CharSequence getPageTitle(int position){
            return titles.get(position);
        }

    }

}
