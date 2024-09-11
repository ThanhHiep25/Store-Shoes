package com.example.shoestore;
import org.glassfish.jersey.servlet.ServletContainer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class ShoestoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShoestoreApplication.class, args);
    }

    @Bean
    public ServletRegistrationBean<ServletContainer> jerseyServlet() {
        ServletRegistrationBean<ServletContainer> registration = new ServletRegistrationBean<>(new ServletContainer(), "/api/*");
        registration.setName("jersey-servlet");
        return registration;
    }
}