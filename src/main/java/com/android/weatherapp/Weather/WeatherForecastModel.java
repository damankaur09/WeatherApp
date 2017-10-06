package com.android.weatherapp.weather;

import com.android.weatherapp.beans.Weather;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface WeatherForecastModel {

    interface OnFinishedListener
    {
        void onFinished(List<Weather> items);
    }

    void findItem(OnFinishedListener listener);
}
