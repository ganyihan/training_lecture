package com.example.business.exception;

public class StockNotEnoughException extends RuntimeException{

  public static final String msg = "stock not enough";

  public StockNotEnoughException() {super(msg);}
}
