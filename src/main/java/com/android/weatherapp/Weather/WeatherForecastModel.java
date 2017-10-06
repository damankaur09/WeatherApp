package com.android.weatherapp.Weather;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface WeatherForecastModel {

    interface OnFinishedListener
    {
        void onFinished(List<String> items);
    }

    void findItem(OnFinishedListener listener);
}
