package com.android.weatherapp.weather;

import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

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
    public void onFinished(List<String> items) {
        if(weatherForecastView!=null)
        {
            weatherForecastView.setItems(items);
            weatherForecastView.hideProgress();
        }
    }
}
