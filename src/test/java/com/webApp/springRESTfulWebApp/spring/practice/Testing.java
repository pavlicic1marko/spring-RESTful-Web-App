package com.webApp.springRESTfulWebApp.spring.practice;

import com.webApp.springRESTfulWebApp.SpringApplicationContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class Testing {
    private static final String NAME = "John Smith";

    @Test
    void PrototypeScopeBeanDifferentName() {

        Person personSingletonA = (Person) SpringApplicationContext.getBean("PersonPrototype");
        Person personSingletonB = (Person) SpringApplicationContext.getBean("PersonPrototype");

        personSingletonA.setName("Marko");
        personSingletonB.setName("Zarko");
        assertNotEquals(personSingletonA.getName(), personSingletonB.getName());

    }

    @Test
    void SingletonScopeBeanDifferentName() {
        Person personSingletonA = (Person) SpringApplicationContext.getBean("PersonSingleton");
        Person personSingletonB = (Person) SpringApplicationContext.getBean("PersonSingleton");
        personSingletonA.setName("Marko");
        personSingletonB.setName("Zarko");
        assertEquals(personSingletonA.getName(), personSingletonB.getName());
    }


}
