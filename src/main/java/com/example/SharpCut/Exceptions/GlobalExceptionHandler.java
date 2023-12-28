package com.example.SharpCut.Exceptions;

import com.example.SharpCut.Exceptions.CustomExceptions.AppointmentDateNotValid;
import com.example.SharpCut.Exceptions.CustomExceptions.CustomGenericException;
import com.example.SharpCut.Exceptions.CustomExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map> handleResourceNotFoundException(ResourceNotFoundException e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map> handleUsernameAlreadyException(SQLIntegrityConstraintViolationException e) {
        Map response = new HashMap<>();
        response.put("message","email already exist!");
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(AppointmentDateNotValid.class)
    public ResponseEntity<Map> handleAppointmentDateNotValidException(AppointmentDateNotValid e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }

    @ExceptionHandler(CustomGenericException.class)
    public ResponseEntity<Map> handleCustomerGenericException(CustomGenericException e) {
        Map response = new HashMap();
        response.put("message",e.getMessage());
        return new ResponseEntity(response,HttpStatusCode.valueOf(409));
    }
}
