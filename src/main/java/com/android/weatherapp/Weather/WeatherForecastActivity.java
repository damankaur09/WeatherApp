package com.android.weatherapp.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.weatherapp.R;

import java.util.List;

public class WeatherForecastActivity extends AppCompatActivity implements WeatherForecastView,AdapterView.OnItemClickListener{

    private ProgressBar progressBar;
    private ListView listView;
    private WeatherForecastPresenter weatherForecastPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        progressBar=(ProgressBar)findViewById(R.id.progress);
        listView=(ListView)findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        weatherForecastPresenter=new WeatherForecastPresenterImpl(this);
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

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.weather, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_refresh:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
        listView.setVisibility(View.VISIBLE);
    }

    @Override
    public void setItems(List<String> items) {
        listView.setAdapter(new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,items));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        weatherForecastPresenter.onItemClicked(position);
    }
}
