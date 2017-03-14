package catmosoerodjo.sr.wildadventure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by qualogy-mac on 3/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Clouds {
    private double all;

    public double getAll() {
        return all;
    }

    public void setAll(double all) {
        this.all = all;
    }
}
