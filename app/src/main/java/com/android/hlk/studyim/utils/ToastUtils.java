package com.android.hlk.studyim.utils;

import android.widget.Toast;

import com.android.hlk.studyim.base.MyApplication;

/**
 * Created by user on 2017/3/2.
 */

public class ToastUtils {

    private static Toast toast;

    public static void toast(String text) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), text, Toast.LENGTH_SHORT);
        }
        toast.setText(text);
        toast.show();
    }
}
