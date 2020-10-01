package tdp2.lucas3.tp0.exceptions;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class CityNotFoundException {
    private final String message;
    private final ZonedDateTime timestamp;


    public CityNotFoundException(String message) {
      this.message = message;
      this.timestamp = ZonedDateTime.now();
    }

    public String getMessage() {
      return message;
    }

    public ZonedDateTime getTimestamp() {
      return timestamp;
    }
}
