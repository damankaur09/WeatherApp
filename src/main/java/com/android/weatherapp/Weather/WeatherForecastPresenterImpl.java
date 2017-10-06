package com.android.weatherapp.weather;

import com.android.weatherapp.beans.Weather;

import java.util.List;


public class WeatherForecastPresenterImpl implements WeatherForecastPresenter,WeatherForecastModel.OnFinishedListener
{
    private WeatherForecastView weatherForecastView;
    private WeatherForecastModel weatherForecastModel;

    public WeatherForecastPresenterImpl(WeatherForecastView weatherForecastView) {
        this.weatherForecastView = weatherForecastView;
        this.weatherForecastModel=new WeatherForecastModelImpl();
    }

    @Override
    public void onResume() {
        if(weatherForecastView!=null)
        {
            weatherForecastView.showProgress();
        }
        weatherForecastModel.findItem(this);
    }

    @Override
    public void onItemClicked(int position) {
        if (weatherForecastView!=null)
        {
            weatherForecastView.showMessage(String.format("Position %d clicked", position + 1));
        }
    }

    @Override
    public void onDestroy() {
        weatherForecastView=null;
    }

    @Override
    public void onFinished(List<Weather> items) {
        if(weatherForecastView!=null)
        {
            weatherForecastView.setItems(items);
            weatherForecastView.hideProgress();
        }
    }
}
