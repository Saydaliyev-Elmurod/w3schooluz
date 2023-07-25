package com.example.timetable.auth;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@AllArgsConstructor
public class AuthController {

    public final AuthService authService;

    @PostMapping("/public/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterDto dto) {
        ResponseEntity.ok(authService.register(dto));
    }

}
