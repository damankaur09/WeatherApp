package com.android.weatherapp.callbacks;

import com.android.weatherapp.beans.Weather;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface Updatable
{
    void onWeatherUpdate(Weather[]  weatherData);
}
