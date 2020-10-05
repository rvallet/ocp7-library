package com.library.msbatch.service;

import javax.mail.MessagingException;
import java.io.IOException;

public interface EmailService {

    void sendEmailWithAttachment() throws MessagingException, IOException;
}
