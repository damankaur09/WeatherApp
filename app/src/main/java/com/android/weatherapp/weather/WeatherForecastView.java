package com.android.weatherapp.weather;

import com.android.weatherapp.beans.Weather;

import java.util.List;


public interface WeatherForecastView {
    void showProgress();

    void hideProgress();

    void setItems(List<Weather> items);

    void showMessage(String Message);
}
