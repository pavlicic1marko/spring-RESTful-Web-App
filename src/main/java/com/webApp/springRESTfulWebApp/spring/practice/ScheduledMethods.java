package com.webApp.springRESTfulWebApp.spring.practice;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@EnableAsync
@Controller
public class ScheduledMethods {

    @Scheduled(fixedRate = 10000)
    void printSomething(){
        System.out.println("this string is printed by the scheduled method with fixedRate = 1000, at " + System.currentTimeMillis() );
    }

    @Async
    @Scheduled(fixedRate = 10000)
    void printSomethingAsync() throws InterruptedException {
        System.out.println("this string is printed with Async method" );
        Thread.sleep(100000);
    }

    @Scheduled(cron="* * * * * ?")
    void printSomethingCron(){
        System.out.println("this string is printed with cron method" );
    }

}
