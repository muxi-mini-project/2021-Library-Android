package com.example.library.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.example.library.R;
import com.example.library.fragment.ChoseBookExtract;

public class ChoseBookExtractActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_extract_fm);

        FragmentManager fm = getSupportFragmentManager();
        Fragment mFragment = fm.findFragmentById(R.id.fg_extract);
        if (mFragment == null){
            mFragment = new ChoseBookExtract();
            fm.beginTransaction().add(R.id.fg_extract,mFragment).commit();
        }
    }




}
