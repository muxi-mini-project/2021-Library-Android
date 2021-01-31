package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.library.data.Book;
import com.example.library.data.BookLab;
import com.example.library.fragment.BookDetailsFragment;

import java.util.List;
import java.util.UUID;

public class BookDetailPagerActivity extends AppCompatActivity {
    private static final String EXTRA_BOOK_ID = "com.example.Library.book_id" ;
    private ViewPager mViewPager;
    private List<Book> mBooks;

/*获取列表项的数据包装成intent*/
    public static Intent newIntent(Context packageContext, UUID bookId){
        Intent intent = new Intent(packageContext,BookDetailPagerActivity.class);
        intent.putExtra(EXTRA_BOOK_ID,bookId);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_book_detail_pager);
    //获取当前id来设置ViewPager的当前页
        final UUID bookId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_BOOK_ID);

        mBooks = BookLab.get(this).getBooks();
        mViewPager = (ViewPager)findViewById(R.id.ac_book_detail_view_pager);
        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

            @Override
            public Fragment getItem(int position) {
                Book book = mBooks.get(position);
                return BookDetailsFragment.newInstance(book.getId());
            }

            @Override
            public int getCount() {
                return mBooks.size();
            }
        });

        //设置初始分页显示项
        for (int i = 0; i < mBooks.size(); i++){
            if (mBooks.get(i).getId().equals(bookId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
