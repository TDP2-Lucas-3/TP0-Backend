package tdp2.lucas3.tp0.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import tdp2.lucas3.tp0.dto.WeatherDto;
import tdp2.lucas3.tp0.dto.WeatherResponseDto;
import tdp2.lucas3.tp0.service.WeatherService;
import org.mockito.Mockito;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Assert;
import tdp2.lucas3.tp0.exceptions.ApiRequestException;



@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ApiController.class)
class CustomerControllerTest {
   @Autowired
   MockMvc mockMvc;

   @Autowired
   ObjectMapper mapper;

   @InjectMocks
   ApiController apiController;

   @Mock
   private WeatherService wService;

   WeatherResponseDto mendozaResponse;
   WeatherResponseDto buenosAiresResponse;
   WeatherDto mendozaWeather;
   WeatherDto buenosAiresWeather;
   String mendozaWeatherJsonString;
   String buenosAiresWeatherJsonString;
   String uri = "/weather";

   @Before
   void setup() throws Exception {
     mendozaResponse = new WeatherResponseDto("50", "17");
     buenosAiresResponse = new WeatherResponseDto("100", "22");
     mendozaWeather = new WeatherDto();
     buenosAiresWeather = new WeatherDto();

     mendozaWeather.setRain("50.0");
     mendozaWeather.setTemp("15.3");
     buenosAiresWeather.setRain("10.0");
     buenosAiresWeather.setTemp("17.0");

     mendozaWeatherJsonString = mapper.writeValueAsString(mendozaWeather);
     buenosAiresWeatherJsonString = mapper.writeValueAsString(buenosAiresWeather);
   }

   @Test
   void givenValidArguments_whenGetIsCalled_thenTheRequestShouldBeSuccessful() throws Exception {

     //given
     uri += "/Mendoza";
     Mockito.when(wService.getWeather("Mendoza")).thenReturn(mendozaResponse);

     //When
     mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andExpect(MockMvcResultMatchers.content().json(mendozaWeatherJsonString))
            .andExpect(MockMvcResultMatchers.status().isOk());

      //Then
      Mockito.verify(wService).getWeather();
   }


   @Test
   void givenBadArguments_whenGetCityNotFoundException_thenBadRequest() throws Exception {

     //Given
     uri += "/Ciudad Falsa";

     //When
     mockMvc.perform(MockMvcRequestBuilders.get(uri))
            .andExpect(result -> Assert.assertTrue(result.getResolvedException() instanceof ApiRequestException))
            .andExpect(result -> Assert.assertEquals("City not found: Ciudad Falsa", result.getResolvedException().getMessage()))
            .andExpect(MockMvcResultMatchers.status().isNotFound());

      //Then
      Mockito.verify(wService).getWeather();
   }
}
