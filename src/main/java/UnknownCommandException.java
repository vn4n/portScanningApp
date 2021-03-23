//exception witch occurs when user entered wrong command (or parameters)
public class UnknownCommandException extends RuntimeException {
    public UnknownCommandException(String message) {
        super(message);
    }
}
