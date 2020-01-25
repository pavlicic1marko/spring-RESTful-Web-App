package com.webApp.springRESTfulWebApp;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CreatingDefaultUsers {

    @EventListener
    public void onApplicationReadyEvent(ApplicationReadyEvent event) {
        System.out.println("goooooooooooooooooooooooooooooooooooo");
    }
}
