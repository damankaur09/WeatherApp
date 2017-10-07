package com.android.weatherapp.login;

import android.os.Handler;
import android.text.TextUtils;

import com.android.weatherapp.utils.Constant;


public class LoginModelImpl implements LoginModel {


    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                //boolean error = false;
                if (TextUtils.isEmpty(username) || (!(TextUtils.equals(username,Constant.USERNAME)))) {
                    listener.onUsernameError();
                    //error = true;
                    return;
                }
                if (TextUtils.isEmpty(password) || (!(TextUtils.equals(password,Constant.PASSWORD)))) {
                    listener.onPasswordError();
                    //error = true;
                    return;
                }
                if ((TextUtils.equals(username,Constant.USERNAME))&& (TextUtils.equals(password,Constant.PASSWORD))) {
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
