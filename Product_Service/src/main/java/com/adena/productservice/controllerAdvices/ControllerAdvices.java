package com.adena.productservice.controllerAdvices;

import com.adena.productservice.Exceptions.ProductNotFound;
import com.adena.productservice.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerAdvices {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ErrorResponseDTO> handleArthmaticExceptions(  ){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("Arthmatic exception");
        ResponseEntity responseEntity = new ResponseEntity<>(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorResponseDTO> handleNullPointerExceptions(  ){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("Object is not Found");
        ResponseEntity responseEntity = new ResponseEntity<>(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }
    @ExceptionHandler(ProductNotFound.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundExceptions(
            ProductNotFound productNotFound
    ){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(productNotFound.getMessage());
        ResponseEntity<ErrorResponseDTO> responseEntity = new ResponseEntity<>(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public  ResponseEntity<ErrorResponseDTO> handleExceptions(  ){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage("Exception occurred");
        ResponseEntity responseEntity = new ResponseEntity(
                errorResponseDTO,
                HttpStatus.INTERNAL_SERVER_ERROR
        );
        return responseEntity;
    }
}
