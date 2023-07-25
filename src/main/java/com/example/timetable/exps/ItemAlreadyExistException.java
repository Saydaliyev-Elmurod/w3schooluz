package com.example.timetable.exps;

public class ItemAlreadyExistException extends RuntimeException{
    public ItemAlreadyExistException(String message) {
        super(message);
    }
}
