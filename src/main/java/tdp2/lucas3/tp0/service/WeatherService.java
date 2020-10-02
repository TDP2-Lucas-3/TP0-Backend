package tdp2.lucas3.tp0.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tdp2.lucas3.tp0.dto.WeatherResponseDto;

import java.time.Duration;

@Service
public class WeatherService {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {

        return builder
                .setConnectTimeout(Duration.ofMillis(3000))
                .setReadTimeout(Duration.ofMillis(3000))
                .build();
    }

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponseDto getWeather(){
        final String uri = "http://api.openweathermap.org/data/2.5/weather?id=6559994&appid=9ce6c563f796ae0c59d72487ddd6c265&units=metric";
        WeatherResponseDto result = restTemplate.getForObject(uri, WeatherResponseDto.class);
        return result;

    }

    public WeatherResponseDto getWeather(String city){
        String host = "http://api.openweathermap.org/data/2.5/weather?q=";
        final String uri = host + city + "&appid=9ce6c563f796ae0c59d72487ddd6c265&units=metric";
        return restTemplate.getForObject(uri, WeatherResponseDto.class);
    }

}
