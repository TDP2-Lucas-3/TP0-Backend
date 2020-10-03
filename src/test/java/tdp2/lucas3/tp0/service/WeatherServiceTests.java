package tdp2.lucas3.tp0.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import tdp2.lucas3.tp0.dto.WeatherResponseDto;
import org.springframework.boot.test.context.SpringBootTest;



@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceTests {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService wService = new WeatherService();

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        String url = "http://api.openweathermap.org/data/2.5/weather?id=6559994&appid=9ce6c563f796ae0c59d72487ddd6c265&units=metric";
        WeatherResponseDto weather = new WeatherResponseDto("100","22");
        Mockito
                .when(restTemplate.getForObject(
                        url, WeatherResponseDto.class)).thenReturn(weather);


        WeatherResponseDto weather2 = wService.getWeather();
        Assert.assertEquals(weather.getClouds().getAll(), weather2.getClouds().getAll());
        Assert.assertEquals(weather.getMain().getTemp(), weather2.getMain().getTemp());
    }

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalledWithAparameter_shouldReturnMockedObject() {
      String host = "http://api.openweathermap.org/data/2.5/weather?q=";
      final String url = host + "Buenos Aires" + "&appid=9ce6c563f796ae0c59d72487ddd6c265&units=metric";

      WeatherResponseDto weather = new WeatherResponseDto("0", "17");
      Mockito
              .when(restTemplate.getForObject(
                      url, WeatherResponseDto.class)).thenReturn(weather);

      WeatherResponseDto weather2 = wService.getWeather("Buenos Aires");
      Assert.assertEquals(weather.getClouds().getAll(), weather2.getClouds().getAll());
      Assert.assertEquals(weather.getMain().getTemp(), weather2.getMain().getTemp());

    }
}
