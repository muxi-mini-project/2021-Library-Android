package com.example.library.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.R;
import com.example.library.RoundImageView;
import com.example.library.fragment.minefragment.mineFragment1;
import com.example.library.fragment.minefragment.mineFragment2;

public class mineFragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private RoundImageView imageView;

    private mineFragment1 fragment1;
    private mineFragment2 fragment2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
        textView1 = v.findViewById(R.id.mine_textView1);
        textView2 = v.findViewById(R.id.mine_textView2);
        textView3 = v.findViewById(R.id.mine_textView3);
        textView4 = v.findViewById(R.id.mine_textView4);
        imageView = v.findViewById(R.id.roundImageView);

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                hideAllFragment(fragmentTransaction);
                setSelect();
                textView3.setSelected(true);
                if (fragment1 == null) {
                    fragment1 = new mineFragment1();
                    fragmentTransaction.add(R.id.fragment, fragment1);
                } else {
                    fragmentTransaction.show(fragment1);
                }
                fragmentTransaction.commit();
            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                hideAllFragment(fragmentTransaction);
                setSelect();
                textView4.setSelected(true);
                if (fragment2 == null) {
                    fragment2 = new mineFragment2();
                    fragmentTransaction.add(R.id.fragment, fragment2);
                } else {
                    fragmentTransaction.show(fragment2);
                }
                fragmentTransaction.commit();
            }
        });

        textView3.performClick();

        return v;
    }


    private void hideAllFragment(FragmentTransaction fragmentTransaction) {
        if (fragment1 != null) {
            fragmentTransaction.hide(fragment1);
        }
        if (fragment2 != null) {
            fragmentTransaction.hide(fragment2);
        }
    }

    /*重置文本点击状态*/
    private void setSelect() {
        textView3.setSelected(false);
        textView4.setSelected(false);
    }
}
