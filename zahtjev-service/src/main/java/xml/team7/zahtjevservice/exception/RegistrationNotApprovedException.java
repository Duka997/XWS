package xml.team7.zahtjevservice.exception;

public class RegistrationNotApprovedException extends RuntimeException {
    public RegistrationNotApprovedException() {
    }

    public RegistrationNotApprovedException(String message) {
        super(message);
    }
}
