package com.example.blog.API;

public class ApiException extends RuntimeException{
    public ApiException(String message){
        super(message);
    }
}
