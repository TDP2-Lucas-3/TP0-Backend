package tdp2.lucas3.tp0.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponseDto implements Serializable {

    private CloudWeatherDto clouds;

    private MainWeatherDto main;

    public CloudWeatherDto getClouds() {
        return clouds;
    }

    public WeatherResponseDto() {
    }

    public WeatherResponseDto(String clouds, String temp) {
        this.clouds = new CloudWeatherDto();
        this.main = new MainWeatherDto();
        this.clouds.setAll(clouds);
        this.main.setTemp(temp);
    }

    public void setClouds(CloudWeatherDto clouds) {
        this.clouds = clouds;
    }

    public MainWeatherDto getMain() {
        return main;
    }

    public void setMain(MainWeatherDto main) {
        this.main = main;
    }
}
