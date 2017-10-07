package com.android.weatherapp.beans;



public class Weather
{
    private String currentTemperature;
    private String minMaxTemperature;
    private String day;
    private String weather;
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

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
