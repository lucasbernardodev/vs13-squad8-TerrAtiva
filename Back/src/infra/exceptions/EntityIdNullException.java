package infra.exceptions;

public class EntityIdNullException extends RuntimeException {
    public EntityIdNullException(String msg) {
        super(msg);
    }
}
