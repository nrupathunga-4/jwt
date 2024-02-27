package com.example.emailtokens.controller;

import com.example.emailtokens.entity.JwtAuthResponse;
import com.example.emailtokens.entity.SignInRequest;
import com.example.emailtokens.entity.SignUpRequest;
import com.example.emailtokens.entity.User;
import com.example.emailtokens.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody SignUpRequest signUpRequest)
    {
        return new ResponseEntity<>(authenticationService.register(signUpRequest), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody SignInRequest signInRequest)
    {
        return new ResponseEntity<>(authenticationService.login(signInRequest),HttpStatus.OK);
    }

    @GetMapping("/validate")
    public String validate(@RequestParam String token)
    {
        authenticationService.validate(token);
        return "Is a Valid token";
    }

}
