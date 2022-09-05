package exception;

public class UserStockNotEnoughException extends RuntimeException{

  public static final String msg = "user stock not enough";

  public UserStockNotEnoughException() {super(msg);}
}
