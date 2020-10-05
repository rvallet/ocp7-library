package com.library.msbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

@Configuration
public class EmailConfig {

    @Bean
    public SimpleMailMessage getSimpleMailMessage() throws MessagingException, IOException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("some@someone");
        simpleMailMessage.setReplyTo("some@someone");

        simpleMailMessage.setTo("some@someone");
        simpleMailMessage.setCc("some@someone");

        simpleMailMessage.setSubject("someSubject");
        simpleMailMessage.setText("<html><body><h1>TEST</h1></body></html>");

        return simpleMailMessage;
/*        MimeMessage msg = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(msg, true);
        mimeMessageHelper.setTo("some@someone");
        mimeMessageHelper.setReplyTo("some@someone");
        mimeMessageHelper.setFrom("some@someone");
        mimeMessageHelper.setSubject("someSubject");
        mimeMessageHelper.setText("<html><body><h1>TEST</h1></body></html>",true);

        mimeMessageHelper.addAttachment("my_test.png", new ClassPathResource("test.png"));

        javaMailSender.send(msg);*/
    }
}
