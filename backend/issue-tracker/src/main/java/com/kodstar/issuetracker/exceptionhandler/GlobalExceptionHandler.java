package com.kodstar.issuetracker.exceptionhandler;


import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.xml.bind.ValidationException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //Http Status : 400 (invalid input)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleInvalidInput(MethodArgumentNotValidException e) {
        return "Invalid input, object invalid";
    }

    //Http Status : 400 (invalid input)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleInvalidInput(HttpMessageNotReadableException e) {
        return "Invalid input, object invalid";
    }

    //Http Status : 400 (invalid id)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public String handleInvalidId(MethodArgumentTypeMismatchException e) {
        return "invalid ID supplied.\n" + e.getMessage();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidQueryParameterException.class)
    public String handleInvalidId(InvalidQueryParameterException e) {
        return e.getMessage();
    }

    //Http Status : 404 (entity not found)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String handleNotExistEntity(NoSuchElementException e) {
        return "Issue not Found!There is no issue with this ID.";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IssueTrackerNotFoundException.class)
    public String handleNotExistEntity(IssueTrackerNotFoundException e) {
        return e.getMessage();
    }

    //Http Status : 404 (Order not found)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String handleNotExistEntityDelete(EmptyResultDataAccessException e) {
        return "Order not found";
    }

    //Http Status : 405 (validation exception)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(ValidationException.class)
    public String handleInvalidValues(ValidationException e) {
        return "Invalid values.\n" + e.getMessage();
    }


    //Http Status : 405 (validation exception)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleInvalidValues(HttpRequestMethodNotSupportedException e) {
        return "Invalid values.\n" + e.getMessage();
    }

//    //Http Status : 409 (conflict) ++
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleEntityIsExist(DataIntegrityViolationException e) {
        return "This is already exist.";
    }

}
