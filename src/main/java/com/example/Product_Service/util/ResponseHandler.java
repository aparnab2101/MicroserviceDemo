package com.example.Product_Service.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseHandler {

    public  static ResponseEntity<Object> generateResponse(HttpStatus status, String message, List<String> errors){
        Map<String,Object> response=new HashMap<>();
        response.put("status",status.value());
        response.put("message",message);
        response.put("errors",errors);
        return new ResponseEntity<>(response,status);
    }
}
