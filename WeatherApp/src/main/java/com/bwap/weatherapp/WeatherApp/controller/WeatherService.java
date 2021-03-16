package com.bwap.weatherapp.WeatherApp.controller;

import com.bwap.weatherapp.WeatherApp.Model.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class WeatherService {

    private OkHttpClient client;
    private Response response;
    private String CityName;
    String unit;
    private String API = "96fef2d13e8d5cff1e8d3c4fb793b840";
    private Report info;


    Gson gson = new GsonBuilder().create();

    public JSONObject getWeather() {

        client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://api.openweathermap.org/data/2.5/weather?q=" + getCityName() + "&units=" + getUnit() + "&appid=96fef2d13e8d5cff1e8d3c4fb793b840").build();

        try {
            response = client.newCall(request).execute();
            return new JSONObject(Objects.requireNonNull(response.body()).string());

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void build() throws JSONException {
        ArrayList<Weather> weather = new ArrayList<>();
        JSONObject report = getWeather();
        Coordinate coordinate = gson.fromJson(report.getJSONObject("coord").toString(), Coordinate.class);
        JSONArray weather1 = report.getJSONArray("weather");

        for (int i = 0; i < weather1.length(); i++) {
            Weather w = gson.fromJson(weather1.getJSONObject(i).toString(), Weather.class);
            weather.add(w);
        }

        String base = report.getString("base");
        Main main = gson.fromJson(report.getJSONObject("main").toString(), Main.class);
        int visibility = report.getInt("visibility");
        Wind wind = gson.fromJson(report.getJSONObject("wind").toString(), Wind.class);
        Cloud cloud = gson.fromJson(report.getJSONObject("clouds").toString(), Cloud.class);
        Long dt = report.getLong("dt");
        Sys sys = gson.fromJson(report.getJSONObject("sys").toString(), Sys.class);
        int timezone = report.getInt("timezone");
        int id = report.getInt("id");
        String name = report.getString("name");
        int code = report.getInt("cod");

        info = new Report(coordinate, weather, base, main, visibility, wind, cloud, dt, sys, timezone, id, name, code);


        System.out.println(info);

    }

    public JSONArray returnWeatherArray() throws JSONException {
        JSONArray weatherArray = getWeather().getJSONArray("weather");
        return weatherArray;
    }

    public JSONObject returnMain() throws JSONException {
        JSONObject mainObj = getWeather().getJSONObject("main");
        return mainObj;
    }

    public JSONObject returnWind() throws JSONException {
        JSONObject wind = getWeather().getJSONObject("wind");
        return wind;
    }

    public JSONObject returnSys() throws JSONException {
        JSONObject sys = getWeather().getJSONObject("sys");
        return sys;
    }

    public String getCityName() {
        return CityName;
    }

    public void setCityName(String cityName) {
        CityName = cityName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
