package com.android.hlk.studyim.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.hlk.studyim.R;
import com.android.hlk.studyim.base.BaseActivity;
import com.android.hlk.studyim.event.FinishEvent;
import com.android.hlk.studyim.listener.LogInListener;
import com.android.hlk.studyim.model.UserModel;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by user on 2017/3/1.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.tv_register)
    TextView tv_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.btn_login)
    public void onLoginClick(View view) {
//        UserModel.getInstance().login(et_username.getText().toString(), et_password.getText().toString(), new LogInListener() {
//
//            @Override
//            public void done(Object o, BmobException e) {
//                if (e == null) {
//                    User user =(User)o;
//                    BmobIM.getInstance().updateUserInfo(new BmobIMUserInfo(user.getObjectId(), user.getUsername(), user.getAvatar()));
//                    startActivity(MainActivity.class, null, true);
//                } else {
//                    toast(e.getMessage() + "(" + e.getErrorCode() + ")");
//                }
//            }
//        });

        UserModel.getInstance().login(et_username.getText().toString().trim(), et_password.getText().toString().trim()
                , new LogInListener() {
                    @Override
                    public void success() {
                        //登陆成功
                    }

                    @Override
                    public void failed() {
                        //登陆失败
                    }
                });
    }

    @OnClick(R.id.tv_register)
    public void onRegisterClick(View view) {
//        startActivity(RegisterActivity.class, null, false);
        startActivity(RegisterActivity.class, true);
    }

    @Subscribe
    public void onEventMainThread(FinishEvent event) {
        finish();
    }
}
