package com.javacurd.springboot.exception;

public class NoDepartmentsFoundException extends RuntimeException{
    public NoDepartmentsFoundException(String message){
        super(message);
    }
}
