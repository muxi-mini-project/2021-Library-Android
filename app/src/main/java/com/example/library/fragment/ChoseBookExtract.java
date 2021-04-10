package com.example.library.fragment;

import android.animation.ObjectAnimator;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.BookExtract.BookDigestData;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.library.BookExtract.BookExtractAdapter;
import com.example.library.BookExtract.BookExtratDetail;
import com.example.library.Interface.BookExtractInterface;
import com.example.library.R;
//import com.example.library.data.Book;
import com.example.library.Searcher.SearchActivity2;
import com.example.library.Searcher.SearchView2;
import com.example.library.activity.LoginActivity;
import com.example.library.data.BookData;
import com.example.library.data.BookExtractLab;
import com.example.library.data.GetDigest;
import com.example.library.edit;


import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChoseBookExtract extends Fragment {
    private static final String TAG = "DQP";
    private SwipeRefreshLayout refresh;
    BookDigestData mData = new BookDigestData();
    private Spinner mChose;
    private Button mEdit;
    private Button mAdd;
    public static List<GetDigest.DataDTO> mBook_extract_list = new ArrayList<>();
    private BookExtractAdapter mAdapter;
    private TextView mBook_extract;
    private EditText mBook_search;
    private RecyclerView mRecyclerView;
    private Context context;
    private static ArrayList<String> list = new ArrayList<>();
    private ArrayAdapter<String> SpinnerAdapter;
    private boolean ture;
    private LinearLayoutManager mLayoutManager;
    private FragmentManager getSupportFragment;
    // private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private LinearLayout mLinearLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.book_zhai, container, false);
        //书摘的RecyclerView
        mRecyclerView = (RecyclerView) view.findViewById(R.id.book_extract_recyclerview);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.book_extract_item);
        refresh=(SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        //设置进度大小
        refresh.setSize(SwipeRefreshLayout.DEFAULT);
        //触发下拉的距离
        refresh.setDistanceToTriggerSync(300);

        getRequest();
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

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getRequest();
            }
        });
        return view;

    }

    private void getRequest() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
        Call<GetDigest> bookExtractCall = mApi.getCall(LoginActivity.token, "0");

        bookExtractCall.enqueue(new Callback<GetDigest>() {
            @Override
            public void onResponse(Call<GetDigest> call, Response<GetDigest> response) {

                Log.d(TAG, "获得书摘" + response.code());
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    //mAdapter = new BookExtractAdapter(getActivity(), mBook_extract_list);
                    Log.d(TAG, "+++=========>" + response.body().toString());
                    mBook_extract_list = response.body().getData();
                    // mData=response.body();
                    updateUI();
                } else if (mBook_extract_list == null) {
                    Toast.makeText(getActivity(), "这里空空如也，快来添加书摘吧~~", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<GetDigest> call, Throwable t) {
                Log.d(TAG, "error ++++++++++");
            }
        });

    }

    private void updateUI() {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new BookExtractAdapter(getActivity(), mBook_extract_list);
        if (mBook_extract_list.size() != 0) {
            Log.e(TAG, "有数据" + mBook_extract_list.get(0));

        } else {
            Log.e(TAG, "!!!!!!!!!!!!!!!!!!!!!!111");
        }

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        //mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setLayoutManager(mLayoutManager);
        mLayoutManager.setOrientation(RecyclerView.VERTICAL);
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
                Intent ab = new Intent(getActivity(), BookExtratDetail.class);
                startActivity(ab);
            }
        });
        mEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ae = new Intent(getActivity(), edit.class);
                startActivity(ae);

            }
        });
        mBook_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity2.class);
                startActivity(intent);
            }
        });
    }
}

