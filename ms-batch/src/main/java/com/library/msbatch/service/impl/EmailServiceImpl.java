package com.library.msbatch.service.impl;

import com.library.msbatch.config.EmailConfig;
import com.library.msbatch.entities.BookLoanEmailReminder;
import com.library.msbatch.service.BookLoanEmailReminderService;
import com.library.msbatch.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    public SimpleMailMessage template;

    @Autowired
    private BookLoanEmailReminderService bookLoanEmailReminderService;

    @Override
    public void sendSimpleMessage(String to, String subject, String text) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("OCP7.msbatch@gmail.com");
        message.setReplyTo("noreply@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);

        LOGGER.info("Envoi d'un email à {} ({} - {})",to , message.getFrom(), text);
        try {
            javaMailSender.send(message);
            //emailConfig.getJavaMailSender().send(message);
        } catch (Exception e) {
            LOGGER.warn(e.getMessage());
        }

    }

    @Override
    public void sendBookLoanReminderEmail() {
        List<BookLoanEmailReminder> bookLoanEmailReminderList = bookLoanEmailReminderService.findBookLoanEmailRemindersByIsEmailSentIsNot(true);
        LOGGER.debug("bookLoanEmailReminderList = {} (filter = {})", bookLoanEmailReminderList.size(), "true");
        if (!bookLoanEmailReminderList.isEmpty()) {
            for (BookLoanEmailReminder bookLoanEmailReminder : bookLoanEmailReminderList) {
                String text = String.format(template.getText(), emailConfig.template(), "<h1>Coucou</h1>" +bookLoanEmailReminder.getUserEmail());
                sendSimpleMessage("remy.vallet@gmail.com", "Objet du message", text);
            }
        }
    }

}

