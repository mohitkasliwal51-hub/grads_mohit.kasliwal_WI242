package com.example.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Component
public class Scheduler {
    // @Scheduled(fixedDelay = 3000, initialDelay = 5000)
    @Scheduled(cron = "0/10 * * * * ?")
    public void scheduleTask() {

        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "dd-MM-yyyy HH:mm:ss.SSS");

        String strDate = dateFormat.format(new Date());

        System.out.println(
                "Namaste! This is a scheduled task. Current time is: "
                        + strDate);
    }
}