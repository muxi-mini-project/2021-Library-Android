package com.example.library.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.library.R;
import com.example.library.Search.SearchActivity;
import com.example.library.fragment.sonfragment.RankFragment;
import com.example.library.fragment.sonfragment.RecommendFragment;
import com.example.library.fragment.sonfragment.SortFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BookCityFragment extends Fragment {
    //暂时考虑子类会用到，用public
    public TextView mEditText;
    private ArrayAdapter<String> mArrayAdapter;
    TabLayout mTableLayout;
    public ViewPager mViewPager;
    public MyAdapter adapter;
    private List<String> titles;//推荐/分类/排行
    private List<Fragment> mFragments;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.father_fg_bookcity,container,false);
        //实例化组件
        mEditText = (TextView) v.findViewById(R.id.book_city_edit_text);
        mTableLayout = v.findViewById(R.id.table_layout_city);
        mViewPager = (ViewPager) v.findViewById(R.id.view_pager);
        mEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
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
        /*mArrayAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1,getDataSource());
        mEditText.setAdapter(mArrayAdapter);
        mEditText.setThreshold(1);//设置输入几个字符后开始出现提示 默认是2*/

        return v;
    }

/*手工设置一个list数组作为数据源*/
    public List<String> getDataSource(){
        List<String> list = new ArrayList<>();
        list.add("pingfande");
        list.add("pingfanderensheng");
        list.add("平凡的父亲");
        return list;
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }*/

    //关联tableLayout和adapter
    private void init(){
        adapter = new MyAdapter(getChildFragmentManager());
        mViewPager.setAdapter(adapter);
        mTableLayout.setupWithViewPager(mViewPager);

    }

    //创建适配器
    class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm){
            super(fm);
        }
        //获得每个页面的下标
        @Override
        public Fragment getItem(int position){
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
    @Override
    public void onPause(){
        super.onPause();
    }

}
