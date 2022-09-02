package com.example.business.exception;

public class SalespersonNotExistException extends RuntimeException{
  private static final String msg = "salesperson not found";

  public SalespersonNotExistException() {
    super(msg);
  }
}
