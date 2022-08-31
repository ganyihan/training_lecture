package com.example.business.exception;

public class UserExistedException extends RuntimeException{
  public static final String msg = "user existed";

  public UserExistedException() {super(msg);}
}
