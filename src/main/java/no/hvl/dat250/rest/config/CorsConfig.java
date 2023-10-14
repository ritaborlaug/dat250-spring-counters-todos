package no.hvl.dat250.rest.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Allow requests from your Angular application's domain
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:4200") // Replace with your Angular app's domain
            .allowedMethods("GET", "POST", "DELETE", "PUT"); // Adjust these methods as needed
    }
    
}
