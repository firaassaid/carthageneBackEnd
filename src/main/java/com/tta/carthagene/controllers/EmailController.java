package com.tta.carthagene.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tta.carthagene.entities.EmailRequest;
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest emailRequest) {
        String toEmail = emailRequest.getEmail();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("said.firaas@gmail.com");
        message.setTo(toEmail);
        message.setSubject("This is your new password 'XRqkA'");
        message.setText("New Password");

        try {
            javaMailSender.send(message);
            return "Email sent successfully!";
        } catch (MailException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return "Failed to send email.";
        }
    }
}

