package tdp2.lucas3.tp0.exceptions;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String name) {
        super("City not found: " + name);
    }

}
