package com.example.emailtokens.serviceimpl;

import com.example.emailtokens.entity.JwtAuthResponse;
import com.example.emailtokens.entity.SignInRequest;
import com.example.emailtokens.entity.SignUpRequest;
import com.example.emailtokens.entity.User;
import com.example.emailtokens.repository.UserRepository;
import com.example.emailtokens.service.AuthenticationService;
import com.example.emailtokens.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    public User register(SignUpRequest signUpRequest)
    {
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setRole(signUpRequest.getRole());
        return userRepository.save(user);
    }

    public JwtAuthResponse login(SignInRequest signInRequest)
    {
        var user = userRepository.findByEmail(signInRequest.getEmail()).orElseThrow(()-> new IllegalArgumentException("Please Provide a Proper Email"));

        var jwt = jwtService.generateToken(user);

        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(jwt);
        return response;
    }

    public void validate(String token)
    {
        jwtService.isTokenExpired(token);
    }

}
