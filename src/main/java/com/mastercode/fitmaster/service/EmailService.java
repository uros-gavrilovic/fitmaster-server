package com.mastercode.fitmaster.service;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@RequiredArgsConstructor
@Service
public class EmailService {

    private JavaMailSender mailSender;

    private MimeMessage mimeMessage;

    public void sendVerificationEmail(String email, String token) {
        System.out.println("Verification email sent to " + email + " with token " + token);
    }
}
