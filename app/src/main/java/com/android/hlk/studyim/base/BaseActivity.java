package com.android.hlk.studyim.base;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

/**
 * Created by user on 2017/3/1.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected final static String NULL = "";
    private Toast toast;

    protected void runOnMain(Runnable runnable) {
        runOnUiThread(runnable);
    }

    public void toast(final Object obj) {
        try {
            runOnMain(new Runnable() {

                @Override
                public void run() {
                    if (toast == null)
                        toast = Toast.makeText(BaseActivity.this, NULL, Toast.LENGTH_SHORT);
                    toast.setText(obj.toString());
                    toast.show();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void startActivity(Class<? extends Activity> target, boolean finish) {
        Intent intent = new Intent();
        intent.setClass(this, target);
//        if (bundle != null)
//            intent.putExtra(getPackageName(), bundle);
        startActivity(intent);
        if (finish)
            finish();
    }

//    public Bundle getBundle() {
//        if (getIntent() != null && getIntent().hasExtra(getPackageName()))
//            return getIntent().getBundleExtra(getPackageName());
//        else
//            return null;
//    }

//    /**
//     * 隐藏软键盘
//     */
//    public void hideSoftInputView() {
//        InputMethodManager manager = ((InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE));
//        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
//            if (getCurrentFocus() != null)
//                manager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }

//    /**
//     * 隐藏软键盘-一般是EditText.getWindowToken()
//     *
//     * @param token
//     */
//    public void hideSoftInput(IBinder token) {
//        if (token != null) {
//            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
//        }
//    }

//    /**
//     * Log日志
//     *
//     * @param msg
//     */
//    public void log(String msg) {
//        if (Config.DEBUG) {
//            Logger.i(msg);
//        }
//    }
}
