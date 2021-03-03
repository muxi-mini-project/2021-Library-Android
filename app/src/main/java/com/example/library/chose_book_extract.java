package com.example.library;


import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;

import androidx.appcompat.app.AppCompatActivity;

import com.example.library.R;

public class chose_book_extract extends AppCompatActivity implements View.OnClickListener {
    private Button chose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

      chose=(Button)findViewById(R.id.chose);
      chose.setOnClickListener(this);
    }

    @Override
    public void onClick(View choes_book_choice) {
        //创建弹出式菜单对象（最低版本11）
        PopupMenu popup = new PopupMenu(this,choes_book_choice);//第二个参数是绑定的那个view
        //获取菜单填充器
        MenuInflater inflater = popup.getMenuInflater();
        //填充菜单
       // inflater.inflate(R.menu.main, popup.getMenu());
        //绑定菜单项的点击事件
        //popup.setOnMenuItemClickListener(this);
        //显示(这一行代码不要忘记了)
        popup.show();
    }


}
