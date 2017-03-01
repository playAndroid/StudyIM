package com.android.hlk.studyim.model;

import android.text.TextUtils;

import com.android.hlk.studyim.bean.User;
import com.android.hlk.studyim.listener.LogInListener;

/**
 * Created by user on 2017/3/1.
 */

public class UserModel {

    private static UserModel instance = new UserModel();

    private UserModel() {
    }

    public static UserModel getInstance() {
        return instance;
    }


    public void login(String username, String password, LogInListener logInListener) {
        if (TextUtils.isEmpty(username)) {
//            listener.internalDone(new BmobException(CODE_NULL, "请填写用户名"));
            return;
        }
        if (TextUtils.isEmpty(password)) {
//            listener.internalDone(new BmobException(CODE_NULL, "请填写密码"));
            return;
        }
        final User user = new User();
        user.setName(username);
        user.setPassword(password);
        //TODO 请求网络 成功
        logInListener.success();

        //TODO 失败
        logInListener.failed();
    }


    public void register(String username, String password, String pwdagain) {
        if (TextUtils.isEmpty(username)) {
//            listener.internalDone(new BmobException(CODE_NULL, "请填写用户名"));
            return;
        }
        if (TextUtils.isEmpty(password)) {
//            listener.internalDone(new BmobException(CODE_NULL, "请填写密码"));
            return;
        }
        if (TextUtils.isEmpty(pwdagain)) {
//            listener.internalDone(new BmobException(CODE_NULL, "请填写确认密码"));
            return;
        }
        if (!password.equals(pwdagain)) {
//            listener.internalDone(new BmobException(CODE_NOT_EQUAL, "两次输入的密码不一致，请重新输入"));
            return;
        }

        User user = new User();
        user.setName(username);
        user.setPassword(password);
        user.setClientId("1");
        // TODO 插入数据库
    }

    /**
     * 查询用户
     */
    public void queryUsers() {

    }

    /**
     * 查询用户信息
     */
    public void queryUserInfo() {

    }

    /**
     * 更新用户资料和会话资料
     */
    public void updateUserInfo() {

    }

    /**
     * 同意添加好友：1、发送同意添加的请求，2、添加对方到自己的好友列表中
     */
    public void agreeAddFriend() {

    }

    public void queryFriends() {

    }

    public void deleteFriend() {

    }

}
