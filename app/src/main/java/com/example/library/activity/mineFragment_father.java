package com.example.library.activity;

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
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.library.Interface.UserDate;
import com.example.library.R;
import com.example.library.RoundImageView;
import com.example.library.data.User;
import com.example.library.data.Users;
import com.example.library.fragment.minefragment_son.mineFragment1;
import com.example.library.fragment.minefragment_son.mineFragment2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class mineFragment_father extends Fragment {

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
    private final int F4 = 0x889;

    private static final int CODE_GALLERY_REQUEST = 0xa0;
    private static final int CODE_CAMERA_REQUEST = 0xa1;
    private static final int CODE_RESULT_REQUEST = 0xa2;

    private Bitmap bitmap;//头像
    private final String path = "/sdcard/Library/Head/";//路径

    public static List<UserData> data = new ArrayList<>();

    private String u_name;
    private String u_motto;
    private String u_picture;
    private String u_token;
    private String u_password;


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
        constraintLayout = v.findViewById(R.id.linearLayout2);
        registerForContextMenu(constraintLayout);
        UpDate();
        Get_user_Date(u_token);
        UpUI();
        Log.d("mineFragment","这里没有错");

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
                    fragment1 =mineFragment1.newInstance(u_token);
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

    public void UpDate(){
        Bundle bundle = getArguments();
        u_name = (String) bundle.getString("getUName");
        u_motto = (String) bundle.getString("getUMotto","这个人很懒，什么都没留下");
        u_picture = (String) bundle.getString("getUPicture");
        u_token = (String) bundle.getString("getUToken");
        u_password = (String) bundle.getString("getUPassword");
        System.out.println("密码是"+u_password);
    }
    public void UpUI(){
        textView1.setText(String.valueOf(u_name));
        textView2.setText(String.valueOf(u_motto));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo info) {
        menu.add(0, F1, 0, "更换头像");
        menu.add(0, F2, 0, "更换姓名");
        menu.add(0, F3, 0, "更换签名");
        menu.add(0, F4, 0,"修改密码");
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
                ChangeName();
                UpDate();
                Toast.makeText(getContext(), u_motto, Toast.LENGTH_SHORT).show();
                break;
            case F3:
                ChangeMotto();
                UpDate();
                Toast.makeText(getContext(), "3", Toast.LENGTH_SHORT).show();
                break;
            case F4:
                ChangePassword();
                UpDate();
                Toast.makeText(getContext(),"4",Toast.LENGTH_SHORT).show();
        }
        return true;
    }
    private void ChangeName(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View v = View.inflate(getContext(), R.layout.change_name, null);
        EditText editText1 =(EditText) v.findViewById(R.id.change_user_name);
        Button button1 = (Button)v.findViewById(R.id.change_user_name_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button button2 = (Button)v.findViewById(R.id.change_user_name_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use_name = editText1.getText().toString();
                Set_user_Date(u_token,use_name,u_motto,u_password,u_picture);
                textView1.setText(use_name);
                System.out.println("用户名是"+use_name);
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.show();
    }
    private void ChangeMotto(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View v = View.inflate(getContext(), R.layout.change_motto, null);
        EditText editText2 =(EditText) v.findViewById(R.id.change_user_motto);
        Button button1 = (Button)v.findViewById(R.id.change_user_motto_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button button2 = (Button)v.findViewById(R.id.change_user_motto_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use_motto = editText2.getText().toString();
                Set_user_Date(u_token,u_name,use_motto,u_password,u_picture);
                textView2.setText(use_motto);
                Log.d("mineFragment","更改的座右铭是"+use_motto+"更改的密码是"+u_password);
                dialog.dismiss();

            }
        });
        dialog.setView(v);
        dialog.show();
    }
    private void ChangePassword() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final AlertDialog dialog = builder.create();
        View v = View.inflate(getContext(), R.layout.change_password, null);
        EditText editText3 = (EditText) v.findViewById(R.id.change_user_password);
        Button button1 = (Button) v.findViewById(R.id.change_user_password_button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Button button2 = (Button) v.findViewById(R.id.change_user_password_button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String use_password = editText3.getText().toString();
                Set_user_Date(u_token, u_name, u_motto, use_password, u_picture);
                textView1.setText(use_password);
                System.out.println("密码是" + use_password);
                dialog.dismiss();
            }
        });
        dialog.setView(v);
        dialog.show();
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


    /**
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

    /**
     *传输数据用的
     * @param name
     * @param picture
     * @param motto
     * @return
     */
    public static mineFragment_father newInstance(String name, String password, String picture, String motto, String token){
        Bundle args = new Bundle();
        args.putString("getUName",name);
        args.putString("getUPicture",picture);
        args.putString("getUMotto",motto);
        args.putString("getUToken",token);
        args.putString("getUPassword",password);
        mineFragment_father Fragment = new mineFragment_father();
        Fragment.setArguments(args);
        return Fragment;
    }

    public void Set_user_Date(String token,String name ,String motto,String password,String picture) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/
        Call<User> user = userDate.setCall(token,new User(name,motto,password,picture));
        user.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    Toast.makeText(getActivity(), "修改成功", Toast.LENGTH_SHORT).show();
                    Log.d("mineFragment","用户名"+name+"座右铭"+motto+"图像"+picture);
                } else {
                    Toast.makeText(getActivity(), "修改失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getActivity(), "没有网络", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void Get_user_Date(String token) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://39.102.42.156:10086")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserDate userDate = retrofit.create(UserDate.class);

        /*接收返回的类*/

        Call<Users> user = userDate.getCall(token);
        user.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.isSuccessful() == true) {
                    u_picture = response.body().getPicture();
                    u_name = response.body().getUser_name();
                    u_motto = response.body().getMotto();
                    UpUI();
                }

                Log.d("GuideActivity", u_name + "还有" + u_motto);

                Toast.makeText(getActivity(), "成功获取信息", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

                Toast.makeText(getActivity(), "获取信息失败", Toast.LENGTH_SHORT).show();
            }


        });
    }


}
