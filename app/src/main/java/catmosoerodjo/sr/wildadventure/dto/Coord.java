package catmosoerodjo.sr.wildadventure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by qualogy-mac on 3/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Coord {
    private double lon;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
