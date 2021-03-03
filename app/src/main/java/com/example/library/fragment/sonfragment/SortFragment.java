package com.example.library.fragment.sonfragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.activity.EachSortActivity;
import com.example.library.fragment.BookCityFragment;

import java.util.ArrayList;
import java.util.List;

public class SortFragment extends BookCityFragment {
    private java.lang.String TAG = "SortFragment";
    private RecyclerView mRecyclerView;
    private List<java.lang.String> nameList = new ArrayList<java.lang.String>();
    private int currentNumber;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.son_fg_recycler,null);

        mRecyclerView = (RecyclerView)view.findViewById(R.id.son_fg_recycler_view);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getParentFragment().getActivity(),2));
        mRecyclerView.setAdapter(new GridAdapter());

        return view;
    }

    private void initData(){
        //为解决list列表只存有最后一个值的暂时解决办法
        String name1 = "现实";
        nameList.add(name1);
        String name2 = "科幻";
        nameList.add(name2);
        String name3 = "艺术";
        nameList.add(name3);
        String name4 = "文学";
        nameList.add(name4);
        String name5 = "武侠";
        nameList.add(name5);
    }

/*adapter and holder*/
class GridAdapter extends RecyclerView.Adapter<GridAdapter.Holder>{

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(getParentFragment().getActivity());
        return new Holder(inflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        initData();
        //String[] nameList = new String[]{"现实","科幻","艺术","文学","武侠"};
        holder.mTextView2.setText("共" + position + "本");
        holder.mTextView1.setText(nameList.get(position).toString());
        currentNumber = position;
        Log.e(TAG,"the currentNumber>>>>>>>" + currentNumber + "<<<<<<");
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    /*holder*/
    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener{
            private TextView mTextView1;
            private ImageView mImageView;
            private TextView mTextView2;


            public Holder(LayoutInflater inflater,ViewGroup parent){
                   super(inflater.inflate(R.layout.item_grid_icon,parent,false));
                   itemView.setOnClickListener(this);

                mTextView1 = (TextView)itemView.findViewById(R.id.sort_text);
                mTextView2 = (TextView)itemView.findViewById(R.id.sort_total);
                mImageView = (ImageView)itemView.findViewById(R.id.sort_pic);
            }

        @Override
        public void onClick(View v) {
            initData();
            currentNumber = getAdapterPosition();
            Intent intent = EachSortActivity.SortIntent(getActivity(),nameList,currentNumber);
            Log.e(TAG,"the currentNumber>>>>>>>" + currentNumber);
            startActivity(intent);
        }
    }//点击后进入相应的列表中。取数据再搞
}


}
