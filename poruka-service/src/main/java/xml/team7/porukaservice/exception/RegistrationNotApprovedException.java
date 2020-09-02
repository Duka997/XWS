package xml.team7.porukaservice.exception;

public class RegistrationNotApprovedException extends RuntimeException {
    public RegistrationNotApprovedException() {
    }

    public RegistrationNotApprovedException(String message) {
        super(message);
    }
}
