package com.example.timetable.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterDto {
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
}
