package com.adena.productservice.Exceptions;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductNotFound extends Exception {
    public ProductNotFound(String message) {
        super(message);
    } ;
}
