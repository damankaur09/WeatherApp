package com.android.weatherapp.login;



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
