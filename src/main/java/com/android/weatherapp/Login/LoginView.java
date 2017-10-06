package com.android.weatherapp.login;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface LoginView
{
    void showProgress();

    void hideProgress();

    void setUsernameError();

    void setPasswordError();

    void navigateToHome();
}
