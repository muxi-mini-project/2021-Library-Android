package com.example.library;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

import java.util.Locale;

public class AddDialog extends AlertDialog {

    Activity context;
    private EditText mAdd_text;
    private Button mCancer;
    private Button mAdd_sure;
    private View.OnClickListener mOnClickListener;

    public AddDialog(@NonNull Activity context) {
        super(context);
        this.context = context;
    }

    public AddDialog(Activity context, int theme, View.OnClickListener clickListener) {
        super(context, theme);
        this.context = context;
        this.mOnClickListener = clickListener;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 指定布局
        this.setContentView(R.layout.edit_add);
        //实例化
        mAdd_text = (EditText) findViewById(R.id.add_text);
        mCancer = (Button) findViewById(R.id.cancer);
        mAdd_sure = (Button) findViewById(R.id.add_sure);

        /*
         * 获取圣诞框的窗口对象及参数对象以修改对话框的布局设置, 可以直接调用getWindow(),表示获得这个Activity的Window
         * 对象,这样这可以以同样的方式改变这个Activity的属性.
         */
       /* Window dialogWindow = this.getWindow();
        WindowManager m = context.getWindowManager();
        Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
    /* p.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕的0.6
    p.width = (int) (d.getWidth() * 0.8); // 宽度设置为屏幕的0.8
        dialogWindow.setAttributes(p);*/


    }
}
