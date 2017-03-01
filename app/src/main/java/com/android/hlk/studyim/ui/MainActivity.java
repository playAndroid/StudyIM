package com.android.hlk.studyim.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.hlk.studyim.R;
import com.android.hlk.studyim.bean.User;

import cn.bmob.newim.BmobIM;
import cn.bmob.newim.core.ConnectionStatus;
import cn.bmob.newim.listener.ConnectListener;
import cn.bmob.newim.listener.ConnectStatusChangeListener;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;

public class MainActivity extends AppCompatActivity {

    private Button connect;
    private Button stop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    private void registerListener() {
        //调用setOnConnectStatusChangeListener方法即可监听到当前长链接的连接状态
        BmobIM.getInstance().setOnConnectStatusChangeListener(new ConnectStatusChangeListener() {
            @Override
            public void onChange(ConnectionStatus status) {
                Log.i("im","" + status.getMsg());
            }
        });

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //建立连接
                //调用connect方法，需要传入一个唯一的用户标示clientId，Demo使用的是Bmob的用户登录系统。
                User user = BmobUser.getCurrentUser(MainActivity.this, User.class);
                BmobIM.connect(user.getClientId(), new ConnectListener() {
                    @Override
                    public void done(String uid, BmobException e) {
                        if (e == null) {
                            Log.d("im", "done: connect success");
                        } else {
                            Log.e("im", e.getErrorCode() + "/" + e.getMessage());
                        }
                    }
                });
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //断开连接
                BmobIM.getInstance().disConnect();
            }
        });
    }

    private void initView() {
        connect = (Button) findViewById(R.id.connect);
        stop = (Button) findViewById(R.id.stop_connect);
    }
}
