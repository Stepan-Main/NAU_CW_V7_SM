package CustomExceptions;

public class NoLibFileException extends Exception{
    public NoLibFileException(String message) {
//        super(message);
        System.out.println(message);
    }
}
