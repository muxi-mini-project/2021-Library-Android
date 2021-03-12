package com.example.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.BookExtractAdapter;
import com.example.library.R;
import com.example.library.Search.BaseViewHolder;
import com.example.library.book_extract;
import com.example.library.data.Book;
import com.example.library.data.BookExtractLab;
import com.example.library.data.BookExtracter;
import com.example.library.data.MyBookExtract;

import java.util.ArrayList;
import java.util.List;

public class ChoseBookExtract extends Fragment {
    private Spinner mChose;
    private Button mEdit;
    private Button mAdd;
    private List<book_extract> mBook_extract;
    private EditText mBook_search;
    private Context context;
    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> SpinnerAdapter;
    private boolean ture;
    private LinearLayoutManager mLayoutManager;
    private BookExtractAdapter mAdapter;
    private List<book_extract> book_extractList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // mChose=(Spinner)view.findViewById(R.id.chose);
        // mChose.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        mAdd = (Button) view.findViewById(R.id.add);
        //mBook_extract =
        mBook_search = (EditText) view.findViewById(R.id.book_search);
        mEdit = (Button) view.findViewById(R.id.edit);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_zhai, container, false);

        mChose = (Spinner) view.findViewById(R.id.chose);
        context = getContext();
        list.add("历史");
        list.add("文学");
        list.add("诗歌");
        list.add("科幻");
        list.add("小说");


        SpinnerAdapter adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);

        mChose.setAdapter(adapter);
        mChose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                mBook_search.setText((String) adapter.getItem(arg2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                mBook_search.setText("全部书摘");
            }
        });

        //书摘的RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.book_kind_recyclerview);
        updateUI();
        //mRecyclerView.setHasFixedSize(ture);
        //mLayoutManager=new LinearLayoutManager(context);
        // mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //  mRecyclerView.setLayoutManager(mLayoutManager);
        // BookExtractLab bookExtractLab=BookExtractLab.get(context);
        //List<MyBookExtract>myBookExtracts=bookExtractLab.getmMyBookextracts();
        //mRecyclerView.setAdapter(new BookExtractAdapter(myBookExtracts,context));


        return view;

    }

    private void updateUI() {
        BookExtractLab bookExtractLab = BookExtractLab.get(getParentFragment().getActivity());
        List<BookExtracter> bookExtracters = bookExtractLab.getBooks();
        mAdapter = new BookExtractAdapter(book_extractList);
        mRecyclerView.setAdapter(mAdapter);

    }

    private class BookExtractHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBook_extract_name;
        private Button mLock;
        private Button mBook_extract_context;
        private TextView mDate;
        private BookExtracter mBookExtracter;

        public BookExtractHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.book_extract_item, null, false));
            View itemView = null;
            itemView.setOnClickListener(this);
            //构造就实例化组件
            mBook_extract_name = (TextView) itemView.findViewById(R.id.book_extract_name);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mLock = (Button) itemView.findViewById(R.id.lock);
            mBook_extract_context = (Button) itemView.findViewById(R.id.book_extract_context);


        }

        public void bind(BookExtracter bookExtracter) {
            mBookExtracter = bookExtracter;
            //mImageView.setImageResource(R.id.rm_book_pic);
            mBook_extract_name.setText(mBookExtracter.getBookextract_name());
            mBook_extract_context.setText(mBookExtracter.getBookextract_information());
            //mInTextView.setText(mBook.getBook_information());
        }

        @Override
        public void onClick(View v) {

        }

        private class BookExtractAdapter extends RecyclerView.Adapter<BookExtractHolder> {
            private List<BookExtracter> mBookExtracters;

           public BookExtractAdapter(List<book_extract> book_extractList) {
                mBook_extract = book_extractList;
            }

            @Override
            public BookExtractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getParentFragment().getActivity());

                return new BookExtractHolder(layoutInflater, parent);
            }

            @Override
            public void onBindViewHolder(@NonNull BookExtractHolder holder, int position) {
                BookExtracter bookExtracter = (BookExtracter) mBookExtracters.get(position);
                holder.bind(bookExtracter);
            }


            @Override
            public int getItemCount() {
                return mBookExtracters.size();
            }
        }

        // @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        //@Override
        public void onNothingSelected(AdapterView<?> parent) {

        }

        //@Override
        // public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        //  mBook_extract
        //}

        //@Override
        //public void onNothingSelected(AdapterView<?> parent) {

        //}
    }
}

