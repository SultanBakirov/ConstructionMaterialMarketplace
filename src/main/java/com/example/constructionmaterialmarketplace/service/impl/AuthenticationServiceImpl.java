package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.config.jwt.JwtUtilities;
import com.example.constructionmaterialmarketplace.dto.request.AuthenticationRequest;
import com.example.constructionmaterialmarketplace.dto.request.RegisterRequest;
import com.example.constructionmaterialmarketplace.dto.response.AuthenticationResponse;
import com.example.constructionmaterialmarketplace.entity.User;
import com.example.constructionmaterialmarketplace.entity.enums.UserRole;
import com.example.constructionmaterialmarketplace.repository.UserRepository;
import com.example.constructionmaterialmarketplace.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final JwtUtilities jwtUtilities;

    public AuthenticationResponse register(RegisterRequest request) throws BadRequestException {
        var existingUser = userRepository.findByEmail(request.getEmail());

        if (existingUser.isPresent()) {
            throw new BadRequestException("User with email " + request.getEmail() + " already exists");
        }

        String passwordPattern = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]+$";
        if (!request.getPassword().matches(passwordPattern)) {
            throw new BadRequestException("Password must contain at least digits and only Latin letters");
        }


        String[] emailParts = request.getEmail().split("@");
        String emailUsername = emailParts[0];
        if (request.getPassword().equals(emailUsername)) {
            throw new BadRequestException("Password should not match email username");
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.getById(2L))
                .build();
        userRepository.save(user);
        var jwtToken = jwtUtilities.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).roleName(user.getRole()).email(user.getEmail()).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String jwtToken = jwtUtilities.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).roleName(user.getRole()).email(user.getEmail()).build();
    }
}
