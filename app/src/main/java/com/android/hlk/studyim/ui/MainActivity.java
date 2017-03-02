package com.android.hlk.studyim.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.hlk.studyim.R;
import com.android.hlk.studyim.base.BaseActivity;
import com.android.hlk.studyim.event.RefreshEvent;
import com.android.hlk.studyim.fragment.ContactFragment;
import com.android.hlk.studyim.fragment.ConversationFragment;
import com.android.hlk.studyim.fragment.SetFragment;

import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import cn.bmob.newim.BmobIM;
import cn.bmob.newim.notification.BmobNotificationManager;


public class MainActivity extends BaseActivity {

    @BindView(R.id.btn_conversation)
    Button btn_conversation;
    @BindView(R.id.btn_contact)
    Button btn_contact;
    @BindView(R.id.btn_set)
    Button btn_set;
    @BindView(R.id.iv_conversation_tips)
    ImageView iv_conversation_tips;
    Button[] mTabs;
    private ConversationFragment conversationFragment;
    private SetFragment setFragment;
    private ContactFragment contactFragment;
    private Fragment[] fragments;
    private int index;
    private int currentTabIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        registerListener();
    }

    private void registerListener() {

    }

    private void initView() {
        mTabs = new Button[3];
        mTabs[0] = btn_conversation;
        mTabs[1] = btn_contact;
        mTabs[2] = btn_set;
        mTabs[0].setSelected(true);
        initTab();
    }

    private void initTab() {
        conversationFragment = new ConversationFragment();
        setFragment = new SetFragment();
        contactFragment = new ContactFragment();
        fragments = new Fragment[]{conversationFragment, contactFragment, setFragment};
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, conversationFragment).
                add(R.id.fragment_container, contactFragment)
                .add(R.id.fragment_container, setFragment)
                .hide(setFragment).hide(contactFragment)
                .show(conversationFragment).commit();
    }

    public void onTabSelect(View view) {
        switch (view.getId()) {
            case R.id.btn_conversation:
                index = 0;
                break;
            case R.id.btn_contact:
                index = 1;
                break;
            case R.id.btn_set:
                index = 2;
                break;
        }
        onTabIndex(index);
    }

    private void onTabIndex(int index) {
        if (currentTabIndex != index) {
            FragmentTransaction trx = getSupportFragmentManager().beginTransaction();
            trx.hide(fragments[currentTabIndex]);
            if (!fragments[index].isAdded()) {
                trx.add(R.id.fragment_container, fragments[index]);
            }
            trx.show(fragments[index]).commit();
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //显示小红点
        checkRedPoint();
        //进入应用后，通知栏应取消
        BmobNotificationManager.getInstance(this).cancelNotification();
    }

    private void checkRedPoint() {
        int count = (int) BmobIM.getInstance().getAllUnReadCount();
        if (count > 0) {
            iv_conversation_tips.setVisibility(View.VISIBLE);
        } else {
            iv_conversation_tips.setVisibility(View.GONE);
        }
        //是否有好友添加的请求
//        if (NewFriendManager.getInstance(this).hasNewFriendInvitation()) {
//            iv_contact_tips.setVisibility(View.VISIBLE);
//        } else {
//            iv_contact_tips.setVisibility(View.GONE);
//        }
    }

    /**
     * 注册自定义消息接收事件
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(RefreshEvent event) {
        checkRedPoint();
    }
}
