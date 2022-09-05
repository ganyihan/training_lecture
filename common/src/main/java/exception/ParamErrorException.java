package exception;

public class ParamErrorException extends RuntimeException{
  public static final String msg = "params error";

  public ParamErrorException() {super(msg);}
}
