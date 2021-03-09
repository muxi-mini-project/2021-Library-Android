package com.example.library.fragment.minefragment;

import android.content.Context;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageButton;
        import android.widget.LinearLayout;
        import android.widget.TextView;
        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import com.example.library.R;
        import com.example.library.data.BookLab;
        import com.example.library.data.MyBook;
        import com.example.library.fragment.MineFragment;

        import java.util.List;

public class mineFragment1 extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private Context context;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_a1, container, false);
        textView1 = (TextView) v.findViewById(R.id.a1_textView1);
        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView2 = (TextView) v.findViewById(R.id.a1_textView2);
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        context = getContext();

        RecyclerView mRecyclerView = (RecyclerView) v.findViewById(R.id.mine_recycle1);
        mRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        BookLab bookLab = BookLab.get(context);
        List<MyBook> mybook = bookLab.getmMyBooks();
        mRecyclerView.setAdapter(new mineFragment1.MyBookAdapt(mybook, context));

        return v;
    }

    class MyBookAdapt extends RecyclerView.Adapter<mineFragment1.MyBookAdapt.Holder> {

        private List<MyBook> mMyBook;
        private Context mContext;

        public MyBookAdapt(List<MyBook> myBook, Context context) {
            this.mMyBook = myBook;
            this.mContext = context;
        }

        @Override
        public mineFragment1.MyBookAdapt.Holder onCreateViewHolder(ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new mineFragment1.MyBookAdapt.Holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(mineFragment1.MyBookAdapt.Holder holder, int position) {
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
}