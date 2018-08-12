package org.gohenry.family;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class GoHenryApplication {

    public static void main(String[] args) {

        SpringApplication.run(GoHenryApplication.class, args);

    }
}
