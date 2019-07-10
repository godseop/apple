package org.godseop.apple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AppleApplication {

    public static void main(String[] args) {
        //SpringApplication.run(AppleApplication.class, args);
        new SpringApplicationBuilder(AppleApplication.class)
                .properties(
                        "spring.config.location=" +
                        "classpath:application.properties" +
                        ", classpath:aws.properties" +
                        ", classpath:datasource.properties" +
                        ", classpath:rest.properties"
                )
                .run(args);
    }

}
