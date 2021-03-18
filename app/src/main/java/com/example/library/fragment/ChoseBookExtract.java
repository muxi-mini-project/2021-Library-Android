package com.example.library.fragment;

import android.app.FragmentManager;
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
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.BookExtractAdapter;
import com.example.library.BookExtratDetail;
import com.example.library.BookExtract;
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
    private BookExtractAdapter mAdapter;
    private TextView mBook_extract;
    private EditText mBook_search;
    private RecyclerView mRecyclerView;
    private Context context;
    private static ArrayList<String> list=new ArrayList<>();
    private ArrayAdapter<String> SpinnerAdapter;
    private boolean ture;
    private FragmentManager getSupportFragment;
    List<BookExtract> mBook_extract_list=new ArrayList<>();


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
        LinearLayoutManager mLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
        //获取数组
        mBook_extract_list = BookExtractLab.get(getActivity()).getBookExtracters();
        mAdapter = new BookExtractAdapter(getActivity(),mBook_extract_list);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnRecyclerViewItemClickListener(new BookExtractAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClicked(int position) {

            }
        });

        //mRecyclerView.setHasFixedSize(ture);
        //BookExtractLab bookExtractLab = BookExtractLab.get(context);
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
                Intent ab=new Intent(getActivity(), BookExtratDetail.class);
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

}



