package com.android.weatherapp.weather;


public interface WeatherForecastPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
