package com.android.weatherapp.callbacks;

import com.android.weatherapp.beans.Weather;

import java.util.List;


public interface Updatable
{
    void onWeatherUpdate(Weather[]  weatherData);
}
