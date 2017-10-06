package com.android.weatherapp.callbacks;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public interface Updatable
{
    void onWeatherUpdate(String[]  weatherData);
}
