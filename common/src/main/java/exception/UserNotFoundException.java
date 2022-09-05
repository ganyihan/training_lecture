package exception;

public class UserNotFoundException extends RuntimeException{

  public static final String msg = "user not found";

  public UserNotFoundException() {super(msg);}
}
