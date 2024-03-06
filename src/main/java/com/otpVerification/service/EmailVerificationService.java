package com.otpVerification.service;

import com.otpVerification.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    static  final Map<String, String> mappedEmailOtp=new HashMap<>();









    public Map<String, String> verifyOtp(String email, String otp) {
           String storedOtp= mappedEmailOtp.get(email);
            Map<String, String> response = new HashMap<>();

           if(storedOtp!=null && storedOtp.equals(otp)){
               User user = userService.getuserByEmail(email);
               if (user!=null){
                   mappedEmailOtp.remove(email);
                   userService.verifyEmail(user);
                   response.put("status", "success");
                   response.put("message", "Email successfully Verified");
               }else {
                   response.put("status", "error");
                   response.put("message", "User not found");
               }
           }else {
               response.put("status", "error");
               response.put("message", "Invalid Otp");
           }
                return response;
    }


    public Map<String, String> sendOtpForLogin(String email) {

        Map<String, String> response= new HashMap<>();
        if (userService.isEmailVerified(email)){
            emailService.sendOtp(email);
            response.put("status","success");
            response.put("message","Otp Sent please check your email ");

        }else {
            response.put("status","success");
            response.put("message","Email not registered ");
        }
        return response;
    }

    public Map<String, String> veryOtpForLogin(String email, String otp) {
        String storedOtp = mappedEmailOtp.get(email);
        Map<String, String> response= new HashMap<>();
        if (storedOtp!=null && storedOtp.equals(otp)){
            mappedEmailOtp.remove(email);
            response.put("status","success");
            response.put("message","User logged in");

        }else {
            response.put("status","error");
            response.put("message","Invalid Otp");

        }
        return response;
    }
}
