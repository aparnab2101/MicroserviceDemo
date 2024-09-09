package com.example.Product_Service.exception;

import java.util.List;

public class InputValidation extends RuntimeException{
    List<String> errors;
    String message;
    public InputValidation(List<String> errors,String message){
        this.errors=errors;
        this.message=message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
