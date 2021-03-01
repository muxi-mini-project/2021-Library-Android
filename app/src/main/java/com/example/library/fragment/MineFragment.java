package com.example.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.R;
import com.example.library.data.BookLab;
import com.example.library.data.MyBook;
import com.example.library.data.MyNotes;
import com.example.library.data.NotesLab;

import java.util.List;

public class MineFragment extends Fragment {
    private ImageButton mImageButton_tou;
    private TextView name;
    private TextView zuoyouming;
    private TextView set;
    private TextView manager;
    private MyBookAdapt mAdapter;
    private Context context;

    private LinearLayoutManager mLayoutManager;
    private LinearLayoutManager nLayoutManager;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine_qyh, container, false);

        context = getContext();
        mImageButton_tou = (ImageButton) v.findViewById(R.id.tou_xiang);
        mImageButton_tou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*更换头像等功能*/
            }
        });

        /*待完善的功能*/
        name = (TextView) v.findViewById(R.id.Name);
        zuoyouming = (TextView) v.findViewById(R.id.zuoyou);
        set = (TextView) v.findViewById(R.id.shezhi);
        manager = (TextView) v.findViewById(R.id.manager);

        /*RecycleView部分*/

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.my_bookshelf_list);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        BookLab bookLab = BookLab.get(context);
        List<MyBook> mybook = bookLab.getmMyBooks();
        mRecyclerView.setAdapter(new MyBookAdapt(mybook, context));


        /*我的发布的RecyclerView*/
        RecyclerView nRecyclerView = (RecyclerView) v.findViewById(R.id.my_publication_list);
        nRecyclerView.setHasFixedSize(true);
        nLayoutManager = new LinearLayoutManager(context);
        nLayoutManager.setOrientation(RecyclerView.VERTICAL);
        nRecyclerView.setLayoutManager(nLayoutManager);
        NotesLab notesLab = NotesLab.get(context);
        List<MyNotes> myNotes = notesLab.getMyNotes();
        nRecyclerView.setAdapter(new MyNoteAdapter(myNotes, context));


        return v;

    }

    class MyBookAdapt extends RecyclerView.Adapter<MyBookAdapt.Holder> {

        private List<MyBook> mMyBook;
        private Context mContext;
        private LayoutInflater mLayoutInflater;

        public MyBookAdapt(List<MyBook> myBook, Context context) {
            this.mMyBook = myBook;
            this.mContext = context;
        }

        @NonNull
        @Override
        public Holder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new Holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(Holder holder, int position) {
            MyBook myBook = mMyBook.get(position);
            holder.bind(myBook);
        }

        @Override
        public int getItemCount() {
            return mMyBook.size();
        }


        class Holder extends RecyclerView.ViewHolder {
            private ImageButton imageButton;
            private TextView textView;

            public Holder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.item_my_book, parent, false));

                imageButton = (ImageButton) itemView.findViewById(R.id.my_book);
                textView = (TextView) itemView.findViewById(R.id.my_book_name);
            }

            public void bind(MyBook myBook) {

                textView.setText(myBook.getBook_name());
            }

        }
    }

    class MyNoteAdapter extends RecyclerView.Adapter<MyNoteAdapter.holder> {
        private List<MyNotes> mMyNotes;
        private Context mContext;


        public MyNoteAdapter(List<MyNotes> myNotes, Context context) {
            this.mMyNotes = myNotes;
            this.mContext = context;
        }

        @NonNull
        @Override
        public holder onCreateViewHolder(@NonNull ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(holder mholder, int position) {
            MyNotes myNotes = mMyNotes.get(position);
            mholder.Bind(myNotes);
        }

        @Override
        public int getItemCount() {
            return mMyNotes.size();
        }

        class holder extends RecyclerView.ViewHolder {

            private ImageView imageView;
            private TextView textView1;
            private TextView textView2;
            private TextView textView3;


            public holder(LayoutInflater inflater, ViewGroup parent) {
                super(inflater.inflate(R.layout.item_my_publication, parent, false));
                imageView = (ImageView) itemView.findViewById(R.id.myNote_pic);
                textView1 = (TextView) itemView.findViewById(R.id.myNote_name);
                textView2 = (TextView) itemView.findViewById(R.id.myNote_Date);
                textView3 = (TextView) itemView.findViewById(R.id.myNote_content);
            }


            public void Bind(MyNotes myNotes) {
                textView1.setText(myNotes.getNoteTitle());
                textView2.setText(myNotes.getNoteDate());
                textView3.setText(myNotes.getCMContent());
            }
        }
    }

}



