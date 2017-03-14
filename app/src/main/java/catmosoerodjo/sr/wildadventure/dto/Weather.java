package catmosoerodjo.sr.wildadventure.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by qualogy-mac on 3/14/17.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    private double id;
    private String main;
    private String description;
    private String icon;

    public double getId() {
        return id;
    }

    public void setId(double id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
