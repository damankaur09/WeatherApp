package com.android.weatherapp.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.android.weatherapp.R;
import com.android.weatherapp.weather.WeatherForecastActivity;

public class LoginActivity extends AppCompatActivity implements LoginView, View.OnClickListener {

    private ProgressBar progressBar;
    private EditText username;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        findViewById(R.id.button).setOnClickListener(this);

        presenter = new LoginPresenterImpl(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showProgress() {

        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setUsernameError() {

        username.setError(getString(R.string.username_error));
    }

    @Override
    public void setPasswordError() {

        password.setError(getString(R.string.password_error));
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(this, WeatherForecastActivity.class));
        finish();
    }

    @Override
    public void onClick(View view) {
       presenter.validateCredentials(username.getText().toString(), password.getText().toString());
       
    }
}
