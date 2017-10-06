package com.android.weatherapp.Weather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public class WeatherForecastModelImpl implements WeatherForecastModel{

    @Override
    public void findItem(OnFinishedListener listener) {
        listener.onFinished(createArrayList());
    }

    private List<String> createArrayList()
    {
        String[] fakeData = {
                 "Today - Sunny-88/63",
                "Tommorow - Foggy-70/46",
                "Weds - Cloudy-72/63",
                "Thurs - Rainy-64/51",
                 "Fri - Foggy-70/46",
                "Sat - Sunny-76/68"
                };
        return Arrays.asList(fakeData);
    }

}
