package com.example.mobiles.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Component
public class GlobalExceptionHandler {

    @ExceptionHandler(MobileNotFoundException.class)
    public ResponseEntity<String> mobileNotFoundException(){
        return new ResponseEntity<String>("Mobile(s) not found !!", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MobileAlreadyExistsException.class)
    public ResponseEntity<String> mobileAlreadyExistsException(){
        return new ResponseEntity<String>("Mobile Already Exists !!",HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MissingRequiredFieldsException.class)
    public ResponseEntity<String> missingRequiredFieldsException(){
        return new ResponseEntity<String>("Missing one or more required fields. Required fields are \nBrand\nModel\nId ",HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(PriceExceededException.class)
    public ResponseEntity<String> priceExceededException(){
        return new ResponseEntity<String>("Price of the mobile can not be more than Rs. 1,50,000",HttpStatus.FORBIDDEN);
    }
}
