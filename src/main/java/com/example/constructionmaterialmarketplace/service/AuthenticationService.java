package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.AuthenticationRequest;
import com.example.constructionmaterialmarketplace.dto.request.RegisterRequest;
import com.example.constructionmaterialmarketplace.dto.response.AuthenticationResponse;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {

    AuthenticationResponse register(RegisterRequest request) throws BadRequestException;

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
