package com.example.library.fragment.minefragment;

        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;

        import androidx.fragment.app.Fragment;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;

        import android.os.Handler;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;
        import android.widget.TextView;

        import com.example.library.R;
        import com.example.library.activity.MyDigestActivity;
        import com.example.library.data.MyNotes;
        import com.example.library.data.Notes;
        import com.example.library.data.NotesLab;

        import java.text.SimpleDateFormat;
        import java.util.Calendar;
        import java.util.List;


public class mineFragment2 extends Fragment {
    private Context context;
    private TextView textView;
    private LinearLayoutManager linearLayoutManager;

    private String time;
    private String time_1 = "yyyy-MM-dd HH:mm";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v=  inflater.inflate(R.layout.fragment_a2, container, false);
        textView = (TextView)v.findViewById(R.id.a2_textView1);

        context = getContext();

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), MyDigestActivity.class);
                startActivity(intent2);
            }
        });

        RecyclerView nRecyclerView = (RecyclerView) v.findViewById(R.id.mine_recycle2);
        nRecyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        nRecyclerView.setLayoutManager(linearLayoutManager);
        NotesLab notesLab = NotesLab.get(context);
        List<MyNotes> myNotes = notesLab.getMyNotes();
        nRecyclerView.setAdapter(new mineFragment2.MyNoteAdapter(myNotes, context));

        return v;
    }

    class MyNoteAdapter extends RecyclerView.Adapter<mineFragment2.MyNoteAdapter.holder> {
        private List<MyNotes> mMyNotes;
        private Context mContext;


        public MyNoteAdapter(List<MyNotes> myNotes, Context context) {
            this.mMyNotes = myNotes;
            this.mContext = context;
        }

        @Override
        public mineFragment2.MyNoteAdapter.holder onCreateViewHolder( ViewGroup parent, int ViewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(mContext);
            return new mineFragment2.MyNoteAdapter.holder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(mineFragment2.MyNoteAdapter.holder mholder, int position) {
            Notes myNotes = mMyNotes.get(position);
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


            public void Bind(Notes myNotes) {
                textView1.setText(myNotes.getNoteTitle());
                //textView2.setText(myNotes.getNoteDate());
                textView3.setText(myNotes.getCMContent());
                handler.post(updateTime);
            }

            /*实现日期读取*/
            Handler handler = new Handler();
            Runnable updateTime = new Runnable() {
                @Override
                public void run() {
                    handler.postDelayed(updateTime,1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(time_1);
                    time =simpleDateFormat.format(Calendar.getInstance().getTime());
                    textView2.setText(time);
                }
            };
        }
    }
}