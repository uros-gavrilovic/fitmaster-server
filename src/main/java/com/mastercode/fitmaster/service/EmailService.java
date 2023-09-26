package com.mastercode.fitmaster.service;

import com.mastercode.fitmaster.util.ApplicationUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@RequiredArgsConstructor
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ApplicationUtils applicationUtils;

    @Async
    public void sendVerificationEmail(String recipientEmail, String mailSubject, String mailText)
            throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setTo(recipientEmail);
        helper.setSubject(mailSubject);
        helper.setText(mailText, true);

        javaMailSender.send(mimeMessage);
    }


    private Properties getEmailProperties() {
        Properties emailProps = new Properties();
        emailProps.put("spring.mail.host", applicationUtils.getApplicationPropsByKey("spring.mail.host"));
        emailProps.put("spring.mail.port", applicationUtils.getApplicationPropsByKey("spring.mail.port"));
        emailProps.put("spring.mail.username", applicationUtils.getApplicationPropsByKey("spring.mail.username"));
        emailProps.put("spring.mail.password", applicationUtils.getApplicationPropsByKey("spring.mail.password"));
        emailProps.put("spring.mail.properties.mail.smtp.auth",
                applicationUtils.getApplicationPropsByKey("spring.mail.properties.mail.smtp.auth"));
        emailProps.put("spring.mail.properties.mail.smtp.starttls.enable",
                applicationUtils.getApplicationPropsByKey("spring.mail.properties.mail.smtp.starttls.enable"));


        return emailProps;
    }
}
