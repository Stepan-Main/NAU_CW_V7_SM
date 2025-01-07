package CustomExceptions;

public class LimitExceededException extends Throwable {
    public LimitExceededException(String s) {
        super(s);
    }
}
