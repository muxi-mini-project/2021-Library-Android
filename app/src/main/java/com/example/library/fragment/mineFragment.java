package com.example.library.fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.service.autofill.UserData;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.R;
import com.example.library.RoundImageView;
import com.example.library.fragment.minefragment.mineFragment1;
import com.example.library.fragment.minefragment.mineFragment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class mineFragment extends Fragment {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private RoundImageView imageView;
    private ConstraintLayout constraintLayout;

    private mineFragment1 fragment1;
    private mineFragment2 fragment2;

    private final int F1 = 0x886;
    private final int F2 = 0x887;
    private final int F3 = 0x888;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    private Bitmap bitmap;//头像
    private final String path = "/sdcard/Library/Head/";//路径

    public static List<UserData> data = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mine, container, false);
/**
 *
 * 无法获取传递的字符串
 *
 */

        textView1 = v.findViewById(R.id.mine_textView1);
        //textView1.setText(s2);
        textView2 = v.findViewById(R.id.mine_textView2);
        //textView2.setText(s3);
        textView3 = v.findViewById(R.id.mine_textView3);
        textView4 = v.findViewById(R.id.mine_textView4);
        imageView = v.findViewById(R.id.roundImageView);
        constraintLayout = v.findViewById(R.id.linearLayout2);
        registerForContextMenu(constraintLayout);
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");
        if (bt != null) {
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);
            imageView.setImageDrawable(drawable);
        }



        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
                hideAllFragment(fragmentTransaction);
                setSelect();
                textView3.setSelected(true);
                if (fragment1 == null) {
                    fragment1 = new mineFragment1();
                    fragmentTransaction.add(R.id.mine_fragment, fragment1);
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
                    fragmentTransaction.add(R.id.mine_fragment, fragment2);
                } else {
                    fragmentTransaction.show(fragment2);
                }
                fragmentTransaction.commit();
            }
        });
        textView3.performClick();
        return v;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
        menu.add(0, F1, 0, "更换头像");
        menu.add(0, F2, 0, "更换姓名");
        menu.add(0, F3, 0, "更换签名");
        menu.setGroupCheckable(0, true, true);
        menu.setHeaderTitle("编辑内容");
    }

    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case F1:
                ShowPicture();
                Toast.makeText(getContext(), "1", Toast.LENGTH_SHORT).show();
                break;
            case F2:
                Toast.makeText(getContext(), "2", Toast.LENGTH_SHORT).show();
                break;
            case F3:
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /*
    显示头像的方法
    */
    private void ShowPicture() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View v = View.inflate(getContext(), R.layout.picture_select, null);
        TextView textView_p1 = (TextView) v.findViewById(R.id.picture_selected_1);
        TextView textView_p2 = (TextView) v.findViewById(R.id.picture_selected_2);
        textView_p1.setOnClickListener(new View.OnClickListener() {
            /*
             * 实现相册中选取
             * */
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_PICK, null);
                intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent1, CODE_GALLERY_REQUEST);
                dialog.dismiss();
            }
        });

        /*
         * 实现拍照
         */
        textView_p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Intent.ACTION_PICK, null);
                intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(), "head.jpg")));
                startActivityForResult(intent2, CODE_CAMERA_REQUEST);
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.show();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent date) {
        switch (requestCode) {
            case CODE_GALLERY_REQUEST:
                if (requestCode == RESULT_OK) {
                    cropPicture(date.getData());
                    break;
                }
            case CODE_CAMERA_REQUEST:
                if (requestCode == RESULT_OK) {
                    File file = new File(Environment.getExternalStorageDirectory(), "/head.jpg");
                    cropPicture(Uri.fromFile(file));
                    break;
                }
            case CODE_RESULT_REQUEST:
                if (date != null) {
                    Bundle extras = date.getExtras();
                    if (extras != null) {

                        bitmap = extras.getParcelable("data");
                        setPictureView(bitmap);
                        imageView.setImageBitmap(bitmap);
                        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_RESULT_REQUEST);
                        }
                    }

                }
                break;
            default:
                break;
        }
        super.onActivityResult(requestCode, resultCode, date);
    }


    /*
     *
     * 裁剪图片
     *
     *  */
    public void cropPicture(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outPutX", 150);
        intent.putExtra("outPutY", 150);
        intent.putExtra("return-date", true);
        startActivityForResult(intent, CODE_RESULT_REQUEST);
    }

    /*
     * 储存图片
     * */
    public void setPictureView(Bitmap bitmap) {
        String status = Environment.getExternalStorageState();
        if (!status.equals(Environment.MEDIA_MOUNTED)) {
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();
        String fileName = path + "head.jpg";
        try {
            b = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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