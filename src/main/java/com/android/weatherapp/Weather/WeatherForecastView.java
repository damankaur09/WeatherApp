package com.android.weatherapp.weather;

import com.android.weatherapp.beans.Weather;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface WeatherForecastView
{
    void showProgress();

    void hideProgress();

    void setItems(List<Weather> items);

    void showMessage(String Message);
}
