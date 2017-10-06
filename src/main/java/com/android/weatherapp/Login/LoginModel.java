package com.android.weatherapp.login;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface LoginModel
{
    interface OnLoginFinishedListener
    {
        void onUsernameError();

        void onPasswordError();

        void onSuccess();
    }

    void login(String username,String password,OnLoginFinishedListener listener);
}
