package com.webApp.springRESTfulWebApp;

import com.webApp.springRESTfulWebApp.security.AppProperties;
import com.webApp.springRESTfulWebApp.spring.practice.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
class SpringResTfulWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringResTfulWebAppApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

    @Bean(name = "AppProperties")
    public AppProperties getAppProperties() {
        return new AppProperties();
    }

	@Bean(name="PersonPrototype")
	@Scope("prototype")
	public Person personPrototype() {
		return new Person();
	}

	@Bean(name="PersonSingleton")
	@Scope("singleton")
	public Person personSingleton() {
		return new Person();
	}

}
