package com.example.binhbt.myapplication.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import android.view.WindowManager;


import com.example.binhbt.myapplication.R;
import com.example.binhbt.myapplication.model.User;
import com.example.binhbt.myapplication.ui.farecyclerview.FARecyclerViewActivity;
import com.example.binhbt.myapplication.ui.mvpdemo.ListAppActivity;
import com.vn.fa.net.ParamBuilder;
import com.vn.fa.net.RequestLoader;

import java.util.List;
import java.util.Map;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @OnClick(R.id.btn_load)
    public void navigateToUserList() {
        //showDialog();
        startActivity(new Intent(this, UserListActivity.class));
    }
    @OnClick(R.id.btn_recyclerview)
    public void showRecyclerViewDemo() {
        //showDialog();
        startActivity(new Intent(this, com.example.binhbt.vegarecyclerview.demo.MainActivity.class));
    }
    @OnClick(R.id.btn_mvp)
    public void showMvpDemo() {
        //showDialog();
        startActivity(new Intent(this, ListAppActivity.class));
    }
    @OnClick(R.id.btn_fa_recyclerview)
    public void showFaRecyclerview() {
        //showDialog();
        startActivity(new Intent(this, FARecyclerViewActivity.class));
    }
    private void showDialog(){
        new AlertDialog.Builder(this)
                .setTitle("Delete entry")
                .setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert).create()
                .show();
    }
    private void cancelRequest(){
        //cancel 1 request
        RequestLoader.getDefault().cancelByTag("sign_up");

        //Cancel all request on screen
        RequestLoader.getDefault().cancelAll(this);
    }
    private void getListUser() {
        Map<String, String> params = new ParamBuilder()
                .add("limit", "20")
                .add("offset", "10")
                .build();
        new RequestLoader.Builder()
                .api(api.listUser(params))
                .callback(new RequestLoader.CallBack<List<User>>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onFinish(List<User> result) {
                    }
                })
                .cancel(new RequestLoader.OnCancelRequest() {
                    @Override
                    public void onCancel() {
                        //Log.e("Cancel ", "cancelled ......");
                    }
                })
                .container(this)
                .tag("get_list")
                .build();
    }

    private void signUp() {
        Map<String, String> params = new ParamBuilder()
                .add("username", "abc")
                .add("detail", "love everything")
                .add("firs_name", "a")
                .add("last_name", "b")
                .add("password", "123456")
                .build();
        new RequestLoader.Builder()
                .api(api.signUp(params))
                .callback(new RequestLoader.CallBack<User>() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onError(Throwable t) {
                    }

                    @Override
                    public void onFinish(User result) {
                    }
                })
                .cancel(new RequestLoader.OnCancelRequest() {
                    @Override
                    public void onCancel() {
                        //Log.e("Cancel ", "cancelled ......");
                    }
                })
                .container(this)
                .tag("sign_up")
                .build();
    }
}
