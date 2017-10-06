package com.android.weatherapp.login;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public class LoginPresenterImpl implements LoginPresenter,LoginModel.OnLoginFinishedListener
{
    private LoginView loginView;
    private LoginModel loginModel;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginModel = new LoginModelImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        if(loginView!=null)
        {
            loginView.showProgress();
        }
        loginModel.login(username,password,this);
    }

    @Override
    public void onDestroy() {
        loginView=null;
    }

    @Override
    public void onUsernameError() {
        if(loginView!=null)
        {
            loginView.setUsernameError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onPasswordError() {

        if (loginView!=null)
        {
            loginView.setPasswordError();
            loginView.hideProgress();
        }
    }

    @Override
    public void onSuccess() {
        if(loginView!=null)
        {
            loginView.navigateToHome();
        }
    }

}
