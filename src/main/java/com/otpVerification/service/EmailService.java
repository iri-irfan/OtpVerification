package com.otpVerification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static com.otpVerification.service.EmailVerificationService.mappedEmailOtp;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;


    public String generateOtp(){
        return String.format("%06d",new java.util.Random().nextInt(1000000));
    }

    public void sendOtp(String email) {
        String otp = generateOtp();
        mappedEmailOtp.put(email,otp);
        sendEmail(email,"Otp For Verification","Your Otp is:"+otp);
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage sm = new SimpleMailMessage();
        sm.setFrom("xyz");
        sm.setTo(to);
        sm.setSubject(subject);
        sm.setText(text);
        javaMailSender.send(sm);

    }
}
