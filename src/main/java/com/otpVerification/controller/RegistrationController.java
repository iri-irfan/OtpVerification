package com.otpVerification.controller;


import com.otpVerification.entity.User;
import com.otpVerification.service.EmailService;
import com.otpVerification.service.EmailVerificationService;
import com.otpVerification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailVerificationService emailVerificationService;


    @PostMapping("/register")
    public Map<String,String> registerUser(@RequestBody User user){
        User registeredUser =userService.registerUser(user);
        emailService.sendOtp(user.getEmail());
        Map<String,String> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "User Registered please check email for verification");
        return  response;
    }
    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestParam String email,@RequestParam String otp){
        return emailVerificationService.verifyOtp(email, otp);
    }
}
