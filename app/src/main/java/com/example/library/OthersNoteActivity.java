package com.example.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.library.data.Notes;
import com.example.library.data.NotesLab;

import java.util.List;
import java.util.UUID;

public class OthersNoteActivity extends AppCompatActivity {
    private static final String EXTRA_NOTE_ID = "com.example.Library.note_id" ;
    private TextView textView3;
    private TextView textView5;
    private TextView textView4;
    private ImageView imageView3;
    private RecyclerView rv_comment;
    private TextView textView6;

    public static  Intent newIntent(Context packageContext, UUID noteId){
        Intent intent = new Intent(packageContext,OthersNoteActivity.class);
        intent.putExtra(EXTRA_NOTE_ID,noteId);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_note_content);
        initView();
        rv_comment.setLayoutManager(new LinearLayoutManager(OthersNoteActivity.this));
        NotesLab notesLab = NotesLab.get(this);
        List<Notes> notes = notesLab.getNotes();
        rv_comment.setAdapter(new CommentAdapter(notes,OthersNoteActivity.this));
    }

    /*实例化组件*/
    private void initView() {
        textView3 = (TextView) findViewById(R.id.textView3);
        textView5 = (TextView) findViewById(R.id.textView5);
        textView4 = (TextView) findViewById(R.id.textView4);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        rv_comment = (RecyclerView) findViewById(R.id.rv_comment);
        textView6 = (TextView) findViewById(R.id.textView6);
    }

}

/*adapter和holder*/
class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.Holder>  {
    private List<Notes> mNotes;
    Context mContext;

    public CommentAdapter(List<Notes> notes,Context context){
        mNotes = notes;
        mContext = context;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        return new Holder(layoutInflater,parent);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Notes notes = mNotes.get(position);
        holder.bind(notes);
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    class Holder extends RecyclerView.ViewHolder {
        private ImageView book_detail_note_pic;
        private TextView book_detail_name;
        private TextView book_detail_date;
        private TextView book_detail_note_content;

        public Holder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.book_notes_list, parent, false));

        //暂时将原他人的书摘内容替换成评论内容，id对应书摘内容和评论内容——前提是一个人只有一条书摘和一条评论
            book_detail_note_pic = (ImageView)itemView.findViewById(R.id.book_detail_note_pic);
            book_detail_name = (TextView)itemView.findViewById(R.id.book_detail_name);
            book_detail_date = (TextView)itemView.findViewById(R.id.book_detail_date);
            book_detail_note_content = (TextView)itemView.findViewById(R.id.book_detail_note_content);

        }

        private void bind(Notes notes){
            book_detail_name.setText(notes.getNoteWriter());
            book_detail_note_content.setText(notes.getCMContent());
            book_detail_date.setText(notes.getNoteDate());
        }
    }
}