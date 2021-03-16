package com.bwap.weatherapp.WeatherApp.Model;

import java.util.ArrayList;

public class Report {

    private Coordinate coord;
    private ArrayList<Weather> weathers=new ArrayList<>();
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Cloud cloud;
    private Long dt;
    private Sys sys;
    private int timezone;
    private int id;
    private String name;
    private int code;

    public Report() {
    }

    public Report(Coordinate coord, ArrayList<Weather> weathers, String base, Main main, int visibility, Wind wind, Cloud cloud, Long dt, Sys sys, int timezone, int id, String name, int code) {
        this.coord = coord;
        this.weathers = weathers;
        this.base = base;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.cloud = cloud;
        this.dt = dt;
        this.sys = sys;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public Coordinate getCoord() {
        return coord;
    }

    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }

    public ArrayList<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(ArrayList<Weather> weathers) {
        this.weathers = weathers;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Cloud getCloud() {
        return cloud;
    }

    public void setCloud(Cloud cloud) {
        this.cloud = cloud;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public int getTimezone() {
        return timezone;
    }

    public void setTimezone(int timezone) {
        this.timezone = timezone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Report{" +
                "coord=" + coord +
                ", weathers=" + weathers +
                ", base='" + base + '\'' +
                ", main=" + main +
                ", visibility=" + visibility +
                ", wind=" + wind +
                ", cloud=" + cloud +
                ", dt=" + dt +
                ", sys=" + sys +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                '}';
    }
}
