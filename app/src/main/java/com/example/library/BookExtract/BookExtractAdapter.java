package com.example.library.BookExtract;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.library.Interface.BookExtractInterface;
import com.example.library.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;


import com.example.library.activity.LoginActivity;
import com.example.library.data.BookDigestData;
import com.example.library.data.BookExtractLab;
import com.example.library.data.GetDigest;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import androidx.fragment.app.FragmentTransaction;

//将数据与每一个条目的界面进行绑定
public class BookExtractAdapter extends RecyclerView.Adapter<BookExtractAdapter.ViewHolder> {

    public Context context;
    private LinearLayout mLinearLayout;
    private PopupWindow mPopWindow;
    public TextView bookname;
    public TextView context1;
    public TextView date;
    public Button lock;
    public EditText title;
    public EditText chapter;
    public EditText summary_information;
    public BookDigestData mData;
    public EditText thought;
    //防止空指针异常
    private List<GetDigest.DataDTO> mBook_extract_list = new ArrayList<>();
    //private OnRecyclerViewItemClickListener mOnRecyclerViewItemClickListener;
    private int position;

    public BookExtractAdapter(Context context, List<GetDigest.DataDTO> mBook_extract_list) {
        this.context = context;
        this.mBook_extract_list = mBook_extract_list;
    }
    //??接口
    //public interface OnRecyclerViewItemClickListener {
    //  void onRecyclerViewItemClicked(int position);
    // }
    //赋值listener？？
    //public void setOnRecyclerViewItemClickListener(OnRecyclerViewItemClickListener clickListener) {
    //  this.mOnRecyclerViewItemClickListener = clickListener;
    // }

    //holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        BookDigestData mData = new BookDigestData();
        BookDigestData.DataDTO mData1 = new BookDigestData.DataDTO();
        TextView bookname;
        TextView context1;
        TextView date;
        Button lock;
        public EditText title;
        public EditText chapter;
        public EditText summary_information;
        BookExtractLab mBookextractLab;
        PopupWindow mPopWindow;
        private static final String TAG = "com.example.library";

        public ViewHolder(@NonNull View view, int position1) {
            super(view);
            bookname = (TextView) view.findViewById(R.id.book_extract_name);
            context1 = (TextView) view.findViewById(R.id.book_extract_context);
            date = (TextView) view.findViewById(R.id.date);
            lock = (Button) view.findViewById(R.id.lock);
            title = (EditText) view.findViewById(R.id.title);
            chapter = (EditText) view.findViewById(R.id.chapter);
            summary_information = (EditText) view.findViewById(R.id.chapter_context);

        }

        private void getrequest(int position1) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://124.71.184.107:10086/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            BookExtractInterface mApi = retrofit.create(BookExtractInterface.class);
            Call<BookDigestData> bookDigestDataCall = mApi.putdigest(LoginActivity.token, mData1.getBook_id());
            bookDigestDataCall.enqueue(new Callback<BookDigestData>() {
                @Override
                public void onResponse(Call<BookDigestData> call, Response<BookDigestData> response) {
                    List<BookDigestData.DataDTO> mBook_extract_list = new ArrayList<>();
                    BookDigestData.DataDTO extract = mBook_extract_list.get(position1);
                    Context context = null;
                    View contentView = LayoutInflater.from(context).inflate(R.layout.delete, null);
                    mPopWindow = new PopupWindow(contentView,
                            ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                    TextView tv1 = (TextView) contentView.findViewById(R.id.edit_digest);
                    /*tv1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(context, BookExtratDetail.class);
                            /*intent.putExtra("书摘名称", extract.getTitle().toString());
                            intent.putExtra("书摘内容", extract.getSummary_information().toString());
                            intent.putExtra("日期", extract.getDate().toString());
                            context.startActivity(intent);
                        }
                    });
                    */
                    mData = response.body();
                }

                @Override
                public void onFailure(Call<BookDigestData> call, Throwable t) {
                    Log.d(TAG, "编辑书摘失败");
                }
            });
        }
    }

    @Override
//第一个方法
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_extract_item, parent, false);
        ViewHolder holder = new ViewHolder(view, position);
        mLinearLayout = (LinearLayout) view.findViewById(R.id.book_extract_item);
        //长按显示弹窗
     /*   registerForContextMenu(mLinearLayout);
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int layoutPosition = holder.getLayoutPosition();
                if (longClickLisenter != null) {
                    longClickLisenter.LongClickLisenter(layoutPosition);
                }
                return false;
            }
        });*/
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.getrequest(position);
        //创建前面实体类对象
        GetDigest.DataDTO extract = mBook_extract_list.get(position);
        //将具体的值赋予子控件
        holder.bookname.setText(extract.getTitle());
        holder.date.setText(extract.getDate());
        //holder.context1.setText(extract.getSummary_information());
        //设置条目中的点击监听
        holder.context1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction =((AppCompatActivity)context).getSupportFragmentManager().beginTransaction();
                Fragment fragment = BookExtratDetail.newInstance(extract.getId());
                fragmentTransaction.add(R.id.guide_fragment,fragment);
                fragmentTransaction.commit();
            }
        });
        holder.lock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(holder, position);
            }
        });
    }


    private void showPopupWindow(@NonNull ViewHolder holder, int position1) {
        GetDigest.DataDTO extract = mBook_extract_list.get(position1);
        View contentView = LayoutInflater.from(context).inflate(R.layout.delete, null);
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        TextView tv1 = (TextView) contentView.findViewById(R.id.edit_digest);
        TextView tv2 = (TextView) contentView.findViewById(R.id.delete_digest);
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookExtratDetail.class);
                intent.putExtra("书摘名称", extract.getTitle().toString());
                //intent.putExtra("书摘内容", extract.getSummary_information().toString());
                intent.putExtra("日期", extract.getDate().toString());
                context.startActivity(intent);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeDigest(position1);
                mPopWindow.dismiss();
            }
        });
        mPopWindow.showAsDropDown(holder.lock);
    }


    private void removeDigest(int position2) {
        mBook_extract_list.remove(position2);
        //删除动画
        notifyItemRemoved(position2);
        notifyDataSetChanged();
    }

    //添加数据
    public void addData(int position) {
        //通知列表添加一条
        GetDigest.DataDTO bookExtracter = new GetDigest.DataDTO();
        bookExtracter.setTitle(bookExtracter.getTitle().toString());
        //bookExtracter.setSummary_information(bookExtracter.getSummary_information().toString());
        bookExtracter.setDate(bookExtracter.getDate().toString());
        mBook_extract_list.add(bookExtracter);

        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mBook_extract_list.size();
    }

    //用以返回长度
   /* @Override
    public int getItemCount() {
      mOnRecyclerViewItemClickListener.onRecyclerViewItemClicked(position);
        return mBook_extract_list.size();
    }*/
}

