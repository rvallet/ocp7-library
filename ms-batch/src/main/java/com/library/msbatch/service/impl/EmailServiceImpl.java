package com.library.msbatch.service.impl;

import com.library.msbatch.config.EmailConfig;
import com.library.msbatch.service.EmailService;
import com.sun.mail.util.MailConnectException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.net.ConnectException;

@Service
public class EmailServiceImpl implements EmailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailConfig emailConfig;

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


/*    @Override
    public void sendEmail() throws IOException, MessagingException {
        LOGGER.info("Envoi d'un email à {} ({} - {})",
                emailConfig.getSimpleMailMessage().getTo(),
                emailConfig.getSimpleMailMessage().getSubject(),
                emailConfig.getSimpleMailMessage().getText());
        javaMailSender.send(emailConfig.getSimpleMailMessage());
    }*/

/*    public SimpleMailMessage getSimpleMailMessage() throws MessagingException, IOException {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("OCP7.msbatch@gmail.com");
        simpleMailMessage.setReplyTo("OCP7.msbatch@gmail.com");

        simpleMailMessage.setTo("OCP7.msbatch@gmail.com");
        simpleMailMessage.setCc("OCP7.msbatch@gmail.com");

        simpleMailMessage.setSubject("someSubject");
        simpleMailMessage.setText("<html><body><h1>TEST</h1></body></html>");

        return simpleMailMessage;
    }*/
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
