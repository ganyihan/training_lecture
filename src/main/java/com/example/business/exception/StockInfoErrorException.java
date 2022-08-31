package com.example.business.exception;

public class StockInfoErrorException extends RuntimeException{
  public static final String msg = "stock info error";

  public StockInfoErrorException() {super(msg);}
}
