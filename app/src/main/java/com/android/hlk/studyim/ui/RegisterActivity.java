package com.android.hlk.studyim.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.hlk.studyim.R;
import com.android.hlk.studyim.base.BaseActivity;
import com.android.hlk.studyim.listener.LogInListener;
import com.android.hlk.studyim.model.UserModel;
import com.android.hlk.studyim.utils.ToastUtils;

import butterknife.BindView;

/**
 * Created by user on 2017/3/1.
 */

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.btn_register)
    Button register;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.et_password_again)
    EditText et_password_again;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel.getInstance().register(et_username.getText().toString(), et_password.getText().toString()
                        , et_password_again.getText().toString(), new LogInListener() {
                            @Override
                            public void success() {
                                ToastUtils.toast("注册成功");
                                finish();
                            }

                            @Override
                            public void failed(String message) {
                                ToastUtils.toast(message);
                            }
                        });
            }
        });
    }
}
