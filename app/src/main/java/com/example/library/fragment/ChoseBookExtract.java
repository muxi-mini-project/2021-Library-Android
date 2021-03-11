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
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.BookExtractAdapter;
import com.example.library.R;
import com.example.library.data.BookExtractLab;
import com.example.library.data.MyBookExtract;

import java.util.ArrayList;
import java.util.List;

public class ChoseBookExtract extends Fragment {
    private Spinner mChose;
    private Button mEdit;
    private Button mAdd;
    private TextView mBook_extract;
    private EditText mBook_search;
    private List<String> list = new ArrayList<String>();
    private ArrayAdapter<String> SpinnerAdapter ;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_zhai, container, false);
        mChose=(Spinner)view.findViewById(R.id.chose);

        Context context = getContext();
        list.add("历史");
        list.add("文学");
        list.add("诗歌");

        SpinnerAdapter adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, list);

        mChose.setAdapter(adapter);
        mChose.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
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
        mRecyclerView=(RecyclerView)view.findViewById(R.id.book_kind_recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager=new LinearLayoutManager(context);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        BookExtractLab bookExtractLab=BookExtractLab.get(context);
        List<MyBookExtract>myBookExtracts=bookExtractLab.getmMyBookextracts();
        mRecyclerView.setAdapter(new BookExtractAdapter(myBookExtracts,context));


        return view;
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

