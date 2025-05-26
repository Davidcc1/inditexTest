package com.inditex.test.exception;

public class ProductPriceNotFoundException extends RuntimeException {
    public ProductPriceNotFoundException(String message) {
        super(message);
    }
}
