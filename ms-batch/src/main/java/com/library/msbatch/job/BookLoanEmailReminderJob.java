package com.library.msbatch.job;

import com.library.msbatch.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@EnableScheduling
@EnableAsync
public class BookLoanEmailReminderJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookLoanEmailReminderJob.class);

    @Autowired
    EmailService emailService;

    @Scheduled(cron="0 0 8 * * ?")
    public void doJob(){
    long t1 = System.currentTimeMillis();
    LOGGER.info("Start Job");

        //emailService.sendSimpleMessage();

    long t2 = System.currentTimeMillis();
    LOGGER.info("End Job ({} ms)", t2-t1);
    }
}
