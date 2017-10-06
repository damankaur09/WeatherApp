package com.android.weatherapp.webservice;

import android.net.Uri;
import android.os.AsyncTask;
import android.text.format.Time;
import android.util.Log;

import com.android.weatherapp.beans.Weather;
import com.android.weatherapp.callbacks.Updatable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * Created by Mohit Goel on 05-10-2017.
 */

public class FetchWeatherTask extends AsyncTask<String,Void,Weather[]> {

    private final String LOG_TAG=FetchWeatherTask.class.getSimpleName();
    public Updatable updatableObject;

    //helper method to format date and time
    private String getReadableDateString(long time)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("EEE MMM dd");
        return dateFormat.format(time);
    }

    //helper method to format high and low of temperature
    private String formatHighLows(double high,double low)
    {
        long roundedHigh=Math.round(high);
        long roundedLow=Math.round(low);

        String highLowStr= "H"+roundedHigh+" "+"L"+roundedLow;
        return highLowStr;
    }

    //parsing of data
    private Weather[] getWeatherDataFromJson(String forecastJsonStr,int numDays) throws JSONException {

        // These are the names of JSON objects that need to be extracted.
        final String OWN_LIST="list";
        final String OWN_WEATHER="weather";
        final String OWN_TEMPERATURE="temp";
        final String OWN_MAX="max";
        final String OWN_MIN="min";
        final String OWN_DESCRIPTION="main";
        final String OWN_CURRENT_TEMPERATURE="day";

        JSONObject forecastJson=new JSONObject(forecastJsonStr);
        JSONArray weatherArray=forecastJson.getJSONArray(OWN_LIST);

        Time dayTime=new Time();
        dayTime.setToNow();

        // we start at the day returned by local time. Otherwise this is a mess.
        int julianStartDay=Time.getJulianDay(System.currentTimeMillis(),dayTime.gmtoff);

        dayTime=new Time();

        Weather [] resultWeahter=new Weather[numDays];

        for(int i=0;i<weatherArray.length();i++)
        {
            String day;
            String description;
            String hhighAndLow;

            // Get the JSON object representing the day
            JSONObject dayForecast=weatherArray.getJSONObject(i);

            long dateTime;
            dateTime = dayTime.setJulianDay(julianStartDay + i);
            day = getReadableDateString(dateTime);

            JSONObject weatherObject = dayForecast.getJSONArray(OWN_WEATHER).getJSONObject(0);
            description = weatherObject.getString(OWN_DESCRIPTION);

            JSONObject temperatureObject = dayForecast.getJSONObject(OWN_TEMPERATURE);
            double high = temperatureObject.getDouble(OWN_MAX);
            double low = temperatureObject.getDouble(OWN_MIN);
            int currentTemperature=temperatureObject.getInt(OWN_CURRENT_TEMPERATURE);

            hhighAndLow=formatHighLows(high,low);

            Weather weather=new Weather();
            weather.setCurrentTemperature(Integer.toString(currentTemperature));
            weather.setDay(day.substring(0,3));
            weather.setMinMaxTemperature(hhighAndLow);
            weather.setWeather(description);

            resultWeahter[i]=weather;
            
        }

        return resultWeahter;
    }

    @Override
    protected Weather[] doInBackground(String... params)
    {
        if(params.length==0)
        {
            return null;
        }

        HttpURLConnection urlConnection=null;
        BufferedReader reader=null;

        String forecastJsonStr=null;

        String format="json";
        String units="metric";
        String numDays="7";
        String appId="273d09eec63fef96db00f20143533b4d";

        final String FORECAST_BASE_URL ="http://api.openweathermap.org/data/2.5/forecast/daily?";
        final String QUERY_PARAM = "q";
        final String FORMAT_PARAM = "mode";
        final String UNITS_PARAM = "units";
        final String DAYS_PARAM = "cnt";
        final String APP_ID = "appid";

        Uri builtUri=Uri.parse(FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM,params[0])
                .appendQueryParameter(UNITS_PARAM,units)
                .appendQueryParameter(FORMAT_PARAM,format)
                .appendQueryParameter(DAYS_PARAM,numDays)
                .appendQueryParameter(APP_ID,appId)
                .build();

        try {

            URL url=new URL(builtUri.toString());
            // Create a request to OpenWeatherMap, and open the connection
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into String
            InputStream inputStream=urlConnection.getInputStream();
            StringBuffer buffer=new StringBuffer();

            if(inputStream==null)
            {
                return null;
            }

            reader=new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while((line=reader.readLine())!=null)
            {
                buffer.append(line+ "\n");
            }
            if(buffer.length()==0)
            {
                return null;
            }
            forecastJsonStr=buffer.toString();

        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }

        try {
            return getWeatherDataFromJson(forecastJsonStr,Integer.parseInt(numDays));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Weather[] result) {
       updatableObject.onWeatherUpdate(result);
    }
}
