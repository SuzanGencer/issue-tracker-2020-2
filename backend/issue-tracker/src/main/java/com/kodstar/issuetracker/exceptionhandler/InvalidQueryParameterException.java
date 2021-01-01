package com.kodstar.issuetracker.exceptionhandler;

public class InvalidQueryParameterException extends RuntimeException{
    public static final String MESSAGE ="There is a invalid Query Parameter. Please check your query parameters! \n%s";
    public InvalidQueryParameterException(String message){
        super(String.format(MESSAGE,message));
    }

}
