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
import com.android.hlk.studyim.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;

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
        registListener();

        loginCache();
    }

    private void loginCache() {
//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if(bmobUser != null){
//            // 允许用户使用应用
//            startActivity(MainActivity.class, true);
//        }else{
//            //缓存用户对象为空时， 可打开用户注册界面…
//        }
    }

    private void registListener() {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.getInstance().login(et_username.getText().toString().trim(), et_password.getText().toString().trim()
                        , new LogInListener() {
                            @Override
                            public void success() {
                                //登陆成功
                                ToastUtils.toast("登陆");
                                startActivity(MainActivity.class, true);
                            }

                            @Override
                            public void failed(String message) {
                                //登陆失败
                                ToastUtils.toast(message);
                            }
                        });
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.class, false);
            }
        });
    }


    @Subscribe
    public void onEventMainThread(FinishEvent event) {
        finish();
    }
}
