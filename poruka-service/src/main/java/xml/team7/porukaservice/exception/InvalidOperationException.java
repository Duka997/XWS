package xml.team7.porukaservice.exception;

public class InvalidOperationException extends RuntimeException {
    public InvalidOperationException() {
    }

    public InvalidOperationException(String message) {
        super(message);
    }
}
