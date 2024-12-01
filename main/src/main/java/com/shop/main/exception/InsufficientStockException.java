package com.shop.main.exception;

public class InsufficientStockException extends RuntimeException {
   public InsufficientStockException (final String message) {
      super(message);
   }

   public InsufficientStockException (final String message, final Throwable cause) {
      super(message, cause);
   }
}
