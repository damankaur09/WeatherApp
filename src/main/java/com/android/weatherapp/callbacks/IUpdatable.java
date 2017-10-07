package com.android.weatherapp.callbacks;

import com.android.weatherapp.beans.Weather;

import java.util.List;


public interface IUpdatable
{
    void onWeatherUpdate(Weather[]  weatherData);
}
