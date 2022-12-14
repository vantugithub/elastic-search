package elasticsearch.demo.exception;

public class ValidateException extends RuntimeException {
    private int errorCode = 400;

    public ValidateException(int errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ValidateException(String message) {
        super(message);
    }

}
