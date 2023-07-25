package com.example.timetable.exps;

public class AppBadRequestException extends RuntimeException{
    public AppBadRequestException(String message) {
        super(message);
    }
}
