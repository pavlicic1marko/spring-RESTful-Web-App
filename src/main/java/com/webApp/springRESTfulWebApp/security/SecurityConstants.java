package com.webApp.springRESTfulWebApp.security;

import com.webApp.springRESTfulWebApp.SpringApplicationContext;

public class  SecurityConstants {
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
    static final String SIGN_UP_URL = "/users";
    static final String LOG_IN_URL = "/users/login";
    static final String H2_CONSOLE = "/h2-console/**";

    //public static final String TOKEN_SECRET = "jf9i4jgu83nf10";
    public static String getTokenSecret() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return appProperties.getTokenSecret();
        //return new AppProperties().getTokenSecret();
    }
}
