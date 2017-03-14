package catmosoerodjo.sr.wildadventure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Created by qualogy-mac on 3/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenWeatherObject {
    private Coord coor;
    private List<Weather> weather;
    private String base;
    private Main main;
    private double visibility;
    private Wind wind;
    private Clouds clouds;
    private double dt;
    private Sys sys;
    private double id;
    private String name;
    private double cod;

    public Coord getCoor() {
        return coor;
    }

    public void setCoor(Coord coor) {
        this.coor = coor;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
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

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public double getDt() {
        return dt;
    }

    public void setDt(double dt) {
        this.dt = dt;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCod() {
        return cod;
    }

    public void setCod(double cod) {
        this.cod = cod;
    }
}
