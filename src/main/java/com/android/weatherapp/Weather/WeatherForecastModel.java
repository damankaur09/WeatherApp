package com.android.weatherapp.weather;

import com.android.weatherapp.beans.Weather;

import java.util.List;


public interface WeatherForecastModel {

    interface OnFinishedListener {
        void onFinished(List<Weather> items);
    }

    void findItem(OnFinishedListener listener);
}
