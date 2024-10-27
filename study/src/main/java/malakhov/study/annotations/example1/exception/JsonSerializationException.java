package malakhov.study.annotations.example1.exception;

public class JsonSerializationException extends RuntimeException {
    public JsonSerializationException(String message) {
        super(message);
    }
}
