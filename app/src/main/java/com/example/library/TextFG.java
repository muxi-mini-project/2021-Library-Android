package com.example.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;


public class TextFG extends Fragment {

    private String content;

    public TextFG (String content){
        this.content = content;
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_test_delete,container,false);
        TextView txt = (TextView)view.findViewById(R.id.txt);
        txt.setText(content);
        return view;
    }

}
