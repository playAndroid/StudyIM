package com.android.hlk.studyim.model;

import android.text.TextUtils;

import com.android.hlk.studyim.bean.User;
import com.android.hlk.studyim.listener.LogInListener;
import com.orhanobut.logger.Logger;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;

import static com.android.hlk.studyim.utils.ToastUtils.toast;

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


    public void login(String username, String password, final LogInListener logInListener) {

        BmobUser bu2 = new BmobUser();
        bu2.setUsername(username);
        bu2.setPassword(password);
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    logInListener.success();
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                } else {
                    Logger.d("im", e.getMessage());
                    logInListener.failed("登陆失败,请注册账号");
                }
            }
        });
    }


    public void register(String username, String password, String pwdagain, final LogInListener logInListener) {
        if (TextUtils.isEmpty(username)) {
            toast("请填写用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            toast("请填写密码");
            return;
        }
        if (TextUtils.isEmpty(pwdagain)) {
            toast("请填写确认密码");
            return;
        }
        if (!password.equals(pwdagain)) {
            toast("两次输入的密码不一致，请重新输入");
            return;
        }

        BmobUser bu = new BmobUser();
        bu.setUsername(username);
        bu.setPassword(password);
//注意：不能用save方法进行注册
        bu.signUp(new SaveListener<User>() {
            @Override
            public void done(User s, BmobException e) {
                if (e == null) {
                    logInListener.success();
                } else {
                    Logger.d("login", e.getMessage());
                    logInListener.failed("用户已被注册,请更换账号重试");
                }
            }
        });

    }

    /**
     * 退出登录
     */
    public void logout() {
        BmobUser.logOut();   //清除缓存用户对象
//        BmobUser currentUser = BmobUser.getCurrentUser(); // 现在的currentUser是null了
    }

    /**
     * 查询用户
     */
    public void queryUsers(String username, int limit, final FindListener<User> listener) {
        BmobQuery<User> query = new BmobQuery<>();
        //去掉当前用户
        try {
            BmobUser user = BmobUser.getCurrentUser(User.class);
            query.addWhereNotEqualTo("username", user.getUsername());
        } catch (Exception e) {
            e.printStackTrace();
        }
        query.addWhereContains("username", username);
        query.setLimit(limit);
        query.order("-createdAt");
//        query.findObjects(MyApplication.getContext(), new FindListener<User>() {
//            @Override
//            public void done(List<User> list, BmobException e) {
//
//            }
//
////            @Override
////            public void onSuccess(List<User> list) {
////                if (list != null && list.size() > 0) {
////                    listener.onSuccess(list);
////                } else {
////                    listener.onError(CODE_NULL, "查无此人");
////                }
////            }
////
////            @Override
////            public void onError(int i, String s) {
////                listener.onError(i, s);
////            }
////        });
//        });
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
