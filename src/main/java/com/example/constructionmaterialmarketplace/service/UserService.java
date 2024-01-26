package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.PasswordChangeRequest;
import com.example.constructionmaterialmarketplace.dto.request.UserRequest;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserResponse getUserById(Long userId);

    UserResponse updateUser(Long userId, UserRequest userRequest);

    SimpleResponse changeUserPassword(Long userId, PasswordChangeRequest passwordChangeRequest);

    List<UserResponse> getAllUsers();

    SimpleResponse deleteUser(Long userId);
}
