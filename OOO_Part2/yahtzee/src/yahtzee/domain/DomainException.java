package yahtzee.domain;

public class DomainException extends RuntimeException {

    public DomainException(Exception e, String message) {
        super(message, e);
    }

    public DomainException(String message) {
        super(message);

    }
}
