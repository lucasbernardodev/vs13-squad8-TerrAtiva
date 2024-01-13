package infra.exceptions;

public class DataFormatInvalidException extends RuntimeException {
    public DataFormatInvalidException(String message) {
        super(message);
    }
}
