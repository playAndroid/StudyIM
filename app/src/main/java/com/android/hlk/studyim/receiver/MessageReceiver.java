package com.android.hlk.studyim.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.bmob.newim.event.MessageEvent;

/**
 * Created by user on 2017/3/1.
 */

public class MessageReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            final MessageEvent event = (MessageEvent) intent.getSerializableExtra("event");
            //开发者可以在这里发应用通知
        }
    }
}
