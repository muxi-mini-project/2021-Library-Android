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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.AboutSwitch;
import com.example.library.BookExtractAdapter;
import com.example.library.BookextractList;
import com.example.library.R;
import com.example.library.Search.BaseViewHolder;
import com.example.library.book_extract;
//import com.example.library.data.Book;
import com.example.library.data.BookExtractLab;
import com.example.library.data.BookExtracter;
import com.example.library.data.MyBookExtract;
import com.example.library.edit;
import com.example.library.data.BookData;
import com.example.library.data.BookExtractLab;
import com.example.library.data.BookExtracter;
import com.example.library.data.MyBookExtract;
import com.example.library.fragment.sonfragment.RankFragment;


import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;

public class ChoseBookExtract extends Fragment {
    private Spinner mChose;
    private Button mEdit;
    private Button mAdd;
    private List<book_extract> mBook_extract_list;
    private TextView mBook_extract;
    private EditText mBook_search;
    private Context context;
    private RecyclerView mRecyclerView;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> SpinnerAdapter;

   // public static List<BookExtracter.> data = new ArrayList<>();
  //  private ArrayAdapter<String> SpinnerAdapter ;
    //private BookExtract mAdapter;
    //private RecyclerView mRecyclerView;
    private boolean ture;
    private LinearLayoutManager mLayoutManager;
    private BookExtractAdapter mAdapter;
   //private List<book_extract> book_extractList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_zhai, container, false);

        mChose = (Spinner) view.findViewById(R.id.chose);
        mChose=(Spinner)view.findViewById(R.id.chose);
        context = getContext();

        list.add("小说");
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
                mBook_extract.setText((String) adapter.getItem(arg2));
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                mBook_extract.setText("全部书摘");
            }
        });

        //书摘的RecyclerView

        mRecyclerView = (RecyclerView) view.findViewById(R.id.book_extract_recyclerview);
       // mRecyclerView.setHasFixedSize(ture);

        mRecyclerView=(RecyclerView)view.findViewById(R.id.book_kind_recyclerview);
        mRecyclerView.setHasFixedSize(ture);
        mLayoutManager=new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setAdapter(mAdapter);
        //updateUI();
       //  BookExtractLab bookExtractLab=BookExtractLab.get(context);
        //List<MyBookExtract>myBookExtracts=bookExtractLab.getmMyBookextracts();
       // mRecyclerView.setAdapter(new BookExtractAdapter(myBookExtracts,context));

        BookExtractLab bookExtractLab=BookExtractLab.get(context);
        List<MyBookExtract>myBookExtracts=bookExtractLab.getmMyBookextracts();
        mRecyclerView.setAdapter(new BookExtractAdapter(myBookExtracts,context));
        upDate();




        return view;

    }


    private void upDate() {
        BookExtractLab bookExtractLab=BookExtractLab.get(getActivity());
        List<BookExtracter> bookExtracters=bookExtractLab.getBookExtracters();
        //mAdapter=new BookExtract(ChoseBookExtract.data);
    }

    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        //mChose.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        mAdd=(Button)view.findViewById(R.id.add);
        mBook_extract=(TextView)view.findViewById(R.id.book_extract);
        mBook_search=(EditText) view.findViewById(R.id.book_search);
        mEdit=(Button)view.findViewById(R.id.edit);
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


//    private void updateUI() {
//        BookExtractLab bookExtractLab = BookExtractLab.get(getActivity());
//        List<BookExtracter> bookExtracters = bookExtractLab.getBookExtracters();
//        mAdapter = new BookExtractAdapter(bookExtracters);
//        mRecyclerView.setAdapter(mAdapter);
//    }
        class BookExtractHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mBook_extract_name;
        private Button mLock;
        private Button mBook_extract_context;
        private TextView mDate;
        private BookextractList mBookExtracter;

        public BookExtractHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.book_extract_item, null, false));
            View itemView = null;
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

        }

        public void bind(BookextractList bookextract) {
            mBookExtracter = bookextract;
            mBook_extract_name.setText(mBookExtracter.getBook_extract_name());
            mBook_extract_context.setText(mBookExtracter.getBook_extract_context());
            mDate.setText(mBookExtracter.getBook_extract_date());
        }

        @Override
        public void onClick(View v) {

        }

        class BookExtractAdapter extends RecyclerView.Adapter<BookExtractHolder> {
            private List<BookextractList> mBookExtracter;

           public BookExtractAdapter(List<BookextractList> book_extractList) {
                mBookExtracter = book_extractList;
            }

            @Override
            public BookExtractHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                LayoutInflater layoutInflater = LayoutInflater.from(getActivity());

                return new BookExtractHolder(layoutInflater,parent);
            }

            @Override
            public void onBindViewHolder(@NonNull BookExtractHolder holder, int position) {
                BookextractList bookExtracter =  mBookExtracter.get(position);
                holder.bind(bookExtracter);
            }



            @Override
            public int getItemCount() {
                return mBookExtracter.size();
            }
        }

        // @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        //@Override
        public void onNothingSelected(AdapterView<?> parent) {


    //@Override
    // public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    //  mBook_extract
    //}


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
}

