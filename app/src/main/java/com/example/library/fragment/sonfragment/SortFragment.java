package com.example.library.fragment.sonfragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.fragment.BookCityFragment;

public class SortFragment extends BookCityFragment {
    private RecyclerView mRecyclerView;

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
        //holder.mTextView1.setText("Sort " + position);
        holder.mTextView2.setText("共" + position + "本");
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    /*holder*/
    class Holder extends RecyclerView.ViewHolder{
            private TextView mTextView1;
            private ImageView mImageView;
            private TextView mTextView2;

            public Holder(LayoutInflater inflater,ViewGroup parent){
                   super(inflater.inflate(R.layout.item_grid_icon,parent,false));

                mTextView1 = (TextView)itemView.findViewById(R.id.sort_text);
                mTextView2 = (TextView)itemView.findViewById(R.id.sort_total);
                mImageView = (ImageView)itemView.findViewById(R.id.sort_pic);
            }
        }//点击后进入相应的列表中。取数据再搞
}


}
