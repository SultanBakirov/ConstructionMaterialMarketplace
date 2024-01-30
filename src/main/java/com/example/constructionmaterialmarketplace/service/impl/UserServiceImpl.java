package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.response.UserResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.PasswordChangeRequest;
import com.example.constructionmaterialmarketplace.dto.request.UserRequest;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.dto.response.UserResponse;
import com.example.constructionmaterialmarketplace.entity.User;
import com.example.constructionmaterialmarketplace.exception.BadRequestException;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.UserRepository;
import com.example.constructionmaterialmarketplace.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserResponseConverter userResponseConverter;

    @Override
    public UserResponse getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id = %d not found ", userId)));
        return userResponseConverter.viewApplication(user);
    }

    private User getAuthenticateUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        return userRepository.findByEmail(login).orElseThrow(() -> new NotFoundException("User not found"));
    }

    @Override
    public UserResponse updateUser(Long userId, UserRequest userRequest) {
        User user = getAuthenticateUser();
        String email = user.getEmail();

        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(userRequest.getEmail()) && !userRequest.getEmail().equals(user.getEmail())) {
                throw new BadRequestException(String.format("This %s already exist!", userRequest.getEmail()));
            }
        }
        user.setUsername(userRequest.getUsername());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());

        userRepository.save(user);

        if (!email.equals(userRequest.getEmail())) {
            return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber());
        } else {
            throw new BadCredentialsException("We couldn't change it!");
        }
    }

    @Override
    public SimpleResponse changeUserPassword(Long userId, PasswordChangeRequest passwordChangeRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with id = %d not found", userId)));
        if (!BCrypt.checkpw(passwordChangeRequest.getOldPassword(), user.getPassword())) {
            throw new BadRequestException("The password is entered incorrectly!");
        }
        user.setPassword(BCrypt.hashpw(passwordChangeRequest.getNewPassword(), BCrypt.gensalt()));
        userRepository.save(user);
        return new SimpleResponse("Password changed successfully!");
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> user = userRepository.findAll();
        return userResponseConverter.getAllUsers(user);
    }

    @Override
    public SimpleResponse deleteUser(Long userId) {
        if (userId == 1) {
            throw new BadRequestException("Can't delete id \"1\" because this id belongs to the Admin");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with this id not found exception"));
        userRepository.delete(user);
        return new SimpleResponse("The user removed!");
    }
}
