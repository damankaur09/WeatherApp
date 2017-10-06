package com.android.weatherapp.Weather;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface WeatherForecastView
{
    void showProgress();

    void hideProgress();

    void setItems(List<String> items);

    void showMessage(String Message);
}
