package com.android.weatherapp.Login;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface LoginPresenter
{
    void validateCredentials(String username,String password);

    void onDestroy();
}
