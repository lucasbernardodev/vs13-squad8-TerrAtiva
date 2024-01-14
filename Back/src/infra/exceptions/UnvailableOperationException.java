package infra.exceptions;

public class UnvailableOperationException extends RuntimeException {
    public UnvailableOperationException(String msg) {
        super(msg);
    }
}
