package my.company.restaurant.exception;

public class AuthException extends RuntimeException {
    public AuthException(String s) {
        super(s);
    }
}
