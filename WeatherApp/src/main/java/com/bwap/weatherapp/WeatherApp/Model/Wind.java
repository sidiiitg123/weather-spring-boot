package com.bwap.weatherapp.WeatherApp.Model;

public class Wind {
    private Double speed;
    private int deg;

    public Wind() {
    }

    public Wind(Double speed, int deg) {
        this.speed = speed;
        this.deg = deg;
    }


    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    @Override
    public String toString() {
        return "Wind{" +
                "speed=" + speed +
                ", deg=" + deg +
                '}';
    }
}
