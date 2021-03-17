package com.example.library.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.BookExtract.BookExtractAdapter;
import com.example.library.BookExtract.BookExtratDetail;
import com.example.library.BookExtract.BookextractLab;
import com.example.library.R;
//import com.example.library.data.Book;
import com.example.library.data.BookExtractLab;
import com.example.library.edit;


import java.util.ArrayList;
import java.util.List;

public class ChoseBookExtract extends Fragment {
    private Spinner mChose;
    private Button mEdit;
    private Button mAdd;
    public static List<BookextractLab> mBook_extract_list = new ArrayList<>();
    private BookExtractAdapter mAdapter;
    private TextView mBook_extract;
    private EditText mBook_search;
    private Context context;
    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> SpinnerAdapter;
    private boolean ture;
    private LinearLayoutManager mLayoutManager;

    public ChoseBookExtract(LayoutInflater layoutInflater, ViewGroup parent) {
    }

    public ChoseBookExtract() {

    }
    //private List<book_extract> book_extractList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_zhai, container, false);

        mChose = (Spinner) view.findViewById(R.id.chose);
        mChose = (Spinner) view.findViewById(R.id.chose);
        context = getContext();

        list.add("小说");
        list.add("历史");
        list.add("文学");
        list.add("诗歌");
        list.add("科幻");

        SpinnerAdapter adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_spinner_item, list);
        mChose.setAdapter(adapter);
        mChose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                mBook_extract.setText((String) adapter.getItem(arg2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                mBook_extract.setText("全部书摘");
            }
        });

        //书摘的RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.book_extract_recyclerview);
        mRecyclerView.setHasFixedSize(ture);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mAdapter = new BookExtractAdapter(mBook_extract_list);
        mRecyclerView.setAdapter(mAdapter);
        BookExtractLab bookExtractLab = BookExtractLab.get(context);
        return view;

    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAdd = (Button) view.findViewById(R.id.add);
        mBook_extract = (TextView) view.findViewById(R.id.book_extract);
        mBook_search = (EditText) view.findViewById(R.id.book_search);
        mEdit = (Button) view.findViewById(R.id.edit);
        mAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ab=new Intent(getActivity(), edit.class);
                startActivity(ab);

            }
        });
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ae=new Intent(getActivity(), edit.class);
                startActivity(ae);

            }
        });
    }

    public class BookExtractHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mBook_extract_name;
        Button mLock;
        Button mBook_extract_context;
        TextView mDate;
        BookextractLab mBookExtracter;
        Context mContext;
        com.example.library.fragment.ChoseBookExtract.BookExtractAdapter mAdapter;


        public BookExtractHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.book_extract_item, null, false));
            //View itemView = null;
            itemView.setOnClickListener(this);
            //构造就实例化组件
            mBook_extract_name = (TextView) itemView.findViewById(R.id.book_extract_name);
            mDate = (TextView) itemView.findViewById(R.id.date);
            mLock = (Button) itemView.findViewById(R.id.lock);
            mBook_extract_context = (Button) itemView.findViewById(R.id.book_extract_context);
            mLock.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //点击决定是否公共

                }
            });
            mBook_extract_context.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent ac=new Intent(getActivity(), BookExtratDetail.class);
                    startActivity(ac);
                }
            });

        }
// public void bind(BookextractLab bookextract) {
//mBookExtracter = bookextract;
// mBook_extract_name.setText(mBookExtracter.getBook_extract_name());
// mBook_extract_context.setText(mBookExtracter.getBook_extract_context());
// mDate.setText(mBookExtracter.getBook_extract_date());
// }
        @Override
        public void onClick(View v) {
        }

        public class BookExtractAdapter extends RecyclerView.Adapter<com.example.library.fragment.ChoseBookExtract.ViewHolder> {
            private List<BookextractLab> mBook_extract = new ArrayList<>();

            public BookExtractAdapter(List<BookextractLab> mBook_extract_list) {

            }

            public class ViewHolder extends RecyclerView.ViewHolder {
                TextView bookname;
                Button context;
                TextView date;
                View bxview;

                public ViewHolder(View view) {
                    super(view);
                    bxview = view;
                    bookname = (TextView) view.findViewById(R.id.book_extract_name);
                    context = (Button) view.findViewById(R.id.book_extract_context);
                    date = (TextView) view.findViewById(R.id.date);
                }

            }

            public BookExtractAdapter(List<BookextractLab> book_extractList, Context context) {
                //列表的赋值
                mBook_extract = book_extractList;
            }

            @Override

            public com.example.library.fragment.ChoseBookExtract.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_extract_item, parent, false);
                com.example.library.fragment.ChoseBookExtract.ViewHolder holder = new com.example.library.fragment.ChoseBookExtract.ViewHolder(view);
                return holder;
            }

            @Override
            public void onBindViewHolder(@NonNull ChoseBookExtract.ViewHolder holder, int position) {
                //创建前面实体类对象
                BookextractLab extract = mBook_extract.get(position);
                //将具体的值赋予子控件
                holder.context.setText(extract.getBook_extract_context());
                holder.date.setText(extract.getBook_extract_date());
            }

            @Override
            public int getItemCount() {
                return 0;
            }

        }

        // @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        //@Override
        public void onNothingSelected(AdapterView<?> parent) {


        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView bookname;
        Button context;
        TextView date;
        View bxview;

        public ViewHolder(View view) {
            super(view);
            bxview = view;
            bookname = (TextView) view.findViewById(R.id.book_extract_name);
            context = (Button) view.findViewById(R.id.book_extract_context);
            date = (TextView) view.findViewById(R.id.date);
        }

    }

    public class BookExtractAdapter extends RecyclerView.Adapter<com.example.library.fragment.ChoseBookExtract.ViewHolder> {
        private List<BookextractLab> mBook_extract = new ArrayList<>();

        public BookExtractAdapter(List<BookextractLab> mBook_extract_list) {

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_extract_item, parent, false);
            com.example.library.fragment.ChoseBookExtract.ViewHolder holder = new com.example.library.fragment.ChoseBookExtract.ViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull com.example.library.fragment.ChoseBookExtract.ViewHolder holder, int position) {
            //创建前面实体类对象
            BookextractLab extract = mBook_extract.get(position);
            //将具体的值赋予子控件
            holder.context.setText(extract.getBook_extract_context());
            holder.date.setText(extract.getBook_extract_date());
        }

        @Override
        public int getItemCount() {
            return mBook_extract.size();
        }
    }
}
