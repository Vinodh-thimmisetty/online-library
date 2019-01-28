package com.vinodh;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@SpringBootApplication
public class LibraryApplication {

/*
    @Autowired
    private RestTemplate restTemplate;
*/

    public static void main(String[] args) {
        SpringApplication.run(LibraryApplication.class, args);
    }

    /**
     * Use this If you want to perform any special action once the spring context is ready
     *
     * @param context
     * @return
     */
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    CommandLineRunner commandLineRunner(ApplicationContext context) {
        return (x) -> {
            Environment environment = context.getEnvironment();
            System.out.println("Activated Profiles : " + Arrays.deepToString(environment.getActiveProfiles()) + ", Default profile :" + Arrays.deepToString(environment.getDefaultProfiles()));
            // Stream.of(context.getBeanDefinitionNames()).forEach(System.out::println);
        };
    }

    @Bean
    @Qualifier(value = "cwsLogin")
    @Order(Ordered.LOWEST_PRECEDENCE)
    CommandLineRunner commandLineRunner() {
        return x -> {

        };
    }
}
