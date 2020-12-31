package com.kodstar.issuetracker.exceptionhandler;

public class IssueTrackerNotFoundException extends RuntimeException{
    private final static String MESSAGE="%s not Found. There is no %s with ID :%s";
    public IssueTrackerNotFoundException(String entityType,String id){
        super(String.format(MESSAGE,entityType,entityType,id));
    }
}
