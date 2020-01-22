package com.webApp.springRESTfulWebApp.security;

import com.webApp.springRESTfulWebApp.SpringApplicationContext;

class SecurityConstants {
    static final long EXPIRATION_TIME = 864000000; // 10 days
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users";
    static final String LOG_IN_URL = "/users/login";

    //public static final String TOKEN_SECRET = "jf9i4jgu83nf10";
    static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
        //return new AppProperties().getTokenSecret();
    }
}
