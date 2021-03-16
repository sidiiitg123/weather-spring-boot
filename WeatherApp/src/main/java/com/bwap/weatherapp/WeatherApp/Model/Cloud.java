package com.bwap.weatherapp.WeatherApp.Model;

public class Cloud {
    private int all;

    public Cloud() {
    }

    public Cloud(int all) {
        this.all = all;
    }

    public int getAll() {
        return all;
    }

    public void setAll(int all) {
        this.all = all;
    }

    @Override
    public String toString() {
        return "Cloud{" +
                "all=" + all +
                '}';
    }
}
