package com.PicPayTotally.PicPayTotally.configs;

import com.PicPayTotally.PicPayTotally.DTOs.ExceptionDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity threatDoubleUser(DataIntegrityViolationException exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO("double user not permited","400");
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity threatNotFindUser(EntityNotFoundException exception){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity threatGeneralException(Exception exception){
        ExceptionDTO exceptionDTO = new ExceptionDTO(exception.getMessage(),"404");
        return ResponseEntity.internalServerError().body(exceptionDTO);
    }




}
