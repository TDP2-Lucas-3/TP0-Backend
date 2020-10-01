package tdp2.lucas3.tp0.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tdp2.lucas3.tp0.dto.WeatherDto;
import tdp2.lucas3.tp0.dto.WeatherResponseDto;
import tdp2.lucas3.tp0.service.WeatherService;
import tdp2.lucas3.tp0.exceptions.CityNotFoundException;

@RestController
public class ApiController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/weather")
    public WeatherDto getWeather() {
        WeatherResponseDto weather = weatherService.getWeather();;
        WeatherDto response = new WeatherDto();
        response.setRain(weather.getClouds().getAll());
        response.setTemp(weather.getMain().getTemp());
        return response;
    }

    //SANTI
    //Hacer un endpoint GET que sea del estilo /weather/{city}
    //Que te permita seleccionar la ciudad de la que queres el clima
    //El objeto de respuesta tiene que ser el mismo de arriba
    //Aca tendrias que hacer el controller y en WeatherService deberias consumir el servicio
    //Cosas que tenes que aprender de esto
    //Estructura de Springboot
    //Manejar response del servicio rest
    //Probar el endpoint con POSTMAN
    //Vas a necesitar Java 8 instalado
    //Opcional: Agregar un campo a la respuesta del REST mapeado del servicio del clima
    @GetMapping("/clima")
    public WeatherDto getWeather(@RequestParam("city") String city) {
      WeatherResponseDto weather = null;
      try {
        weather = weatherService.getWeather(city);
      } catch (CityNotFoundException ex) {
        throw ex;
      }
      WeatherDto response =  new WeatherDto();
      response.setRain(weather.getClouds().getAll());
      response.setTemp(weather.getMain().getTemp());
      return response;
    }

}
