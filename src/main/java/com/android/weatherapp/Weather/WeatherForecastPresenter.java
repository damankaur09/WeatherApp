package com.android.weatherapp.Weather;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface WeatherForecastPresenter {

    void onResume();

    void onItemClicked(int position);

    void onDestroy();
}
