package com.android.weatherapp.weather;

import com.android.weatherapp.beans.Weather;
import com.android.weatherapp.callbacks.IUpdatable;
import com.android.weatherapp.webservice.FetchWeatherTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class WeatherForecastModelImpl implements WeatherForecastModel, IUpdatable {

    private List<Weather> list = new ArrayList<>();
    private OnFinishedListener listener;

    @Override
    public void findItem(OnFinishedListener listener) {
        FetchWeatherTask weatherTask = new FetchWeatherTask();
        weatherTask.updatableObject = this;
        weatherTask.execute("chandigarh");
        this.listener = listener;
    }

    @Override
    public void onWeatherUpdate(Weather[] weatherData) {
        list.clear();
        list.addAll(Arrays.asList(weatherData));
        listener.onFinished(list);
    }
}
