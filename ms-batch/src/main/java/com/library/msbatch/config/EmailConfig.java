package com.library.msbatch.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);

        mailSender.setUsername("OCP7.msbatch@gmail.com");
        mailSender.setPassword("wbkpmlynrqmyjlug"); // PW_Compte = msbatch.OCP7

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.user", "OCP7.msbatch@gmail.com");
        props.put("mail.smtp.password","wbkpmlynrqmyjlug");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");

        return mailSender;
    }

    @Bean
    public SimpleMailMessage template() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("OCP7.msbatch@gmail.com");
        message.setFrom("OCP7.msbatch@gmail.com");
        message.setText(
                "Bonjour :" +
                        "\n%s" +
                        "\n");
        return message;
    }

}
