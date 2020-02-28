package com.webApp.springRESTfulWebApp.security;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


@Component
public class AppProperties {

    @Autowired
    private Environment env;

    private ModelMapper modelMapper;

    String getTokenSecret() {
        return env.getProperty("tokenSecret");
    }

    public Boolean isAccountEnabledByDefault(){ return env.getProperty("isAccountEnabledByDefault",Boolean.class) ;}
}
