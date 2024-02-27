package com.example.emailtokens.service;

import com.example.emailtokens.entity.JwtAuthResponse;
import com.example.emailtokens.entity.SignInRequest;
import com.example.emailtokens.entity.SignUpRequest;
import com.example.emailtokens.entity.User;

public interface AuthenticationService {


    User register(SignUpRequest signUpRequest);
    JwtAuthResponse login(SignInRequest signInRequest);

    void validate(String token);

}
