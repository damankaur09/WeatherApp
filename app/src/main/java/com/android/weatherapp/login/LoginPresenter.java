package com.android.weatherapp.login;

public interface LoginPresenter {
    void validateCredentials(String username, String password);

    void onDestroy();
}
