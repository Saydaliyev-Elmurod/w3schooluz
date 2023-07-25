package com.example.timetable.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public ResponseEntity<?> register(RegisterDto dto) {
        String email  = dto.getEmail();



    }
}
