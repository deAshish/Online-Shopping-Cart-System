package miu.edu.pm.project.onlineshoppingcartsystem.email.controller;

import miu.edu.pm.project.onlineshoppingcartsystem.email.dto.EmailDto;
import miu.edu.pm.project.onlineshoppingcartsystem.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import java.io.IOException;


@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping(value = "/sendemail")
    public String sendEmail(@RequestBody EmailDto email) throws MessagingException, IOException {
        emailService.sendEmail(email);
        return "Email sent successfully";
    }

}


