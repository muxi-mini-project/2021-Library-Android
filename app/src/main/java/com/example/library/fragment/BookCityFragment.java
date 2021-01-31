package com.example.library.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.TableLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.library.R;
import com.example.library.fragment.sonfragment.RankFragment;
import com.example.library.fragment.sonfragment.RecommendFragment;
import com.example.library.fragment.sonfragment.SortFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class BookCityFragment extends Fragment {
    //暂时考虑子类会用到，用public
    public EditText mEditText;
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
        mEditText = (EditText)v.findViewById(R.id.book_city_edit_text);
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

        return v;
    }

    //关联tableLayout和
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
