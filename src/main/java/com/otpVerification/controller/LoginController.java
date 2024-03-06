package com.otpVerification.controller;

import com.otpVerification.service.EmailService;
import com.otpVerification.service.EmailVerificationService;
import com.otpVerification.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private EmailService emailService;
    @Autowired
    private EmailVerificationService emailVerificationService;
    @Autowired
    private UserService  userService;


    @PostMapping("/send-otp")
    public Map<String ,String> sendOtpForLogin(@RequestParam String email){
          return emailVerificationService.sendOtpForLogin(email);

    }

    @PostMapping("/verify-otp")
    public Map<String ,String> veryOtpForLogin(@RequestParam String email, @RequestParam String otp) {
        return emailVerificationService.veryOtpForLogin(email, otp);
    }
}
