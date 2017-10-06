package com.android.weatherapp.weather;

import com.android.weatherapp.callbacks.Updatable;
import com.android.weatherapp.webservice.FetchWeatherTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public class WeatherForecastModelImpl implements WeatherForecastModel,Updatable{

    private List<String> list=new ArrayList<>();

    @Override
    public void findItem(OnFinishedListener listener) {
        FetchWeatherTask weatherTask=new FetchWeatherTask();
        weatherTask.updatableObject=this;
        weatherTask.execute("560034");
        listener.onFinished(list);
    }

    @Override
    public void onWeatherUpdate(String [] weatherData) {
        list.clear();
        for(String s:weatherData)
        {
            list.add(s);
        }
    }
}
