package com.example.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.library.R;
import com.example.library.data.BookData;
import com.example.library.fragment.BookDetailsFragment;
import com.example.library.fragment.sonfragment.RankFragment;
import com.example.library.fragment.sonfragment.RecommendFragment;

import java.util.List;

public class BookDetailPagerActivity extends AppCompatActivity {
    private static final String EXTRA_BOOK_ID = "com.example.Library.book_id" ;
    private static final String TAG = "BookDetailPagerActivity";
    private ViewPager mViewPager;
    private List<BookData.DataBean> mBooks;

/*获取列表项的数据包装成intent*/
    public static Intent newIntent(Context packageContext, int bookId){
        Intent intent = new Intent(packageContext,BookDetailPagerActivity.class);
        intent.putExtra(EXTRA_BOOK_ID,bookId);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_book_detail_pager);
//获取当前id来设置ViewPager的当前页
        final int bookId = (int) getIntent()
                .getSerializableExtra(EXTRA_BOOK_ID);
        Log.e(TAG,"pager 的id  " + bookId);
//根据条件获取推荐或排行的数据
        if (bookId > 50){
            mBooks = RecommendFragment.data;
        }else {
            mBooks = RankFragment.data2;
        }

        mViewPager = (ViewPager)findViewById(R.id.ac_book_detail_view_pager);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentPagerAdapter(fm) {

            @Override
            public Fragment getItem(int position) {
                BookData.DataBean book = mBooks.get(position);
                Log.e(TAG,"fragment启动的bookId是    "+ book.getBook_id());

                return BookDetailsFragment.newInstance(book.getBook_id());
            }

            @Override
            public int getCount() {
                return mBooks.size();
            }
        });

        //设置初始分页显示项
        for (int i = 0; i < mBooks.size(); i++){
            if (mBooks.get(i).getBook_id().equals(bookId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
