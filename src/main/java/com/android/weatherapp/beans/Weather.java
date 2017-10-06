package com.android.weatherapp.beans;

/**
 * Created by Mohit Goel on 06-10-2017.
 */

public class Weather
{
    private String currentTemperature;
    private String minMaxTemperature;
    private String day;
    private String weather;

    public String getCurrentTemperature() {
        return currentTemperature;
    }

    public void setCurrentTemperature(String currentTemperature) {
        this.currentTemperature = currentTemperature;
    }

    public String getMinMaxTemperature() {
        return minMaxTemperature;
    }

    public void setMinMaxTemperature(String minMaxTemperature) {
        this.minMaxTemperature = minMaxTemperature;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }
}
