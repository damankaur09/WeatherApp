package com.android.weatherapp.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.weatherapp.R;
import com.android.weatherapp.beans.Weather;

import java.util.List;

public class WeatherForecastActivity extends AppCompatActivity implements WeatherForecastView, AdapterView.OnItemClickListener {

    private ProgressBar progressBar;
    private TextView tvCurrentTemp, tvTodayWeather, tvTodayHighLowTemp, tvCityName;
    private TextView tvDay2Name, tvDay3Name, tvDay4Name;
    private ImageView ivTodayWeatherIcon, ivDay2WeatherIcon, ivDay3WeatherIcon, ivDay4WeatherIcon;
    private WeatherForecastPresenter weatherForecastPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        progressBar = (ProgressBar) findViewById(R.id.progress);
        tvTodayWeather = (TextView) findViewById(R.id.today_weather_textview);
        tvCurrentTemp = (TextView) findViewById(R.id.current_temp_textView);
        tvTodayHighLowTemp = (TextView) findViewById(R.id.today_high_low_temp_textview);
        tvDay2Name = (TextView) findViewById(R.id.day2_weather_textview);
        tvDay3Name = (TextView) findViewById(R.id.day3_weather_textview);
        tvDay4Name = (TextView) findViewById(R.id.day4_weather_textview);
        tvCityName = (TextView) findViewById(R.id.city_name_textview);

        ivTodayWeatherIcon = (ImageView) findViewById(R.id.today_weather_icon_imageview);
        ivDay2WeatherIcon = (ImageView) findViewById(R.id.day2_weather_icon_imageview);
        ivDay3WeatherIcon = (ImageView) findViewById(R.id.day3_weather_icon_imageview);
        ivDay4WeatherIcon = (ImageView) findViewById(R.id.day4_weather_icon_imageview);
        weatherForecastPresenter = new WeatherForecastPresenterImpl(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        weatherForecastPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        weatherForecastPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                onResume();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);

    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);

    }

    @Override
    public void setItems(List<Weather> items) {
        Log.d("ListView", items.toString());
        for (int i = 0; i < items.size(); i++) {
            Weather weather = items.get(i);
            switch (i) {
                case 0:
                    tvTodayWeather.setText(weather.getWeather());
                    tvCurrentTemp.setText(weather.getCurrentTemperature() + (char) 0x00B0);
                    tvTodayHighLowTemp.setText(weather.getMinMaxTemperature());
                    ivTodayWeatherIcon.setImageResource(getWeatherIcon(weather.getWeather()));
//                    tvCityName.setText(weather.getCityName());
                    break;
                case 1:
                    tvDay2Name.setText(weather.getDay());
                    ivDay2WeatherIcon.setImageResource(getWeatherIcon(weather.getWeather()));
                    break;
                case 2:
                    tvDay3Name.setText(weather.getDay());
                    ivDay3WeatherIcon.setImageResource(getWeatherIcon(weather.getWeather()));
                    break;
                case 3:
                    tvDay4Name.setText(weather.getDay());
                    ivDay4WeatherIcon.setImageResource(getWeatherIcon(weather.getWeather()));
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        weatherForecastPresenter.onItemClicked(position);
    }

    private int getWeatherIcon(String weather) {
        switch (weather.toUpperCase()) {
            case "CLEAR":
                return R.drawable.sun;
            case "RAIN":
                return R.drawable.rain;
            default:
                return R.drawable.cloud;
        }
    }
}
