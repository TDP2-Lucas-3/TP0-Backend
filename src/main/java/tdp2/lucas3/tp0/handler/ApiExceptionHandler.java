package tdp2.lucas3.tp0.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tdp2.lucas3.tp0.exceptions.ApiRequestException;
import tdp2.lucas3.tp0.exceptions.CityNotFoundException;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = {ApiRequestException.class})
  public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {
    CityNotFoundException exception = new CityNotFoundException(e.getMessage());
    return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
  }
}
