package com.otpVerification.service;

import com.otpVerification.entity.User;
import com.otpVerification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        User registeredUser = userRepository.save(user);
        return registeredUser;
    }

    public User getuserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user;
    }

    public void verifyEmail(User user) {
        user.setEmailVerified(true);
        userRepository.save(user);
    }

    public boolean isEmailVerified(String email) {
        User user = userRepository.findByEmail(email);
        return user!=null && user.isEmailVerified();

    }
}
