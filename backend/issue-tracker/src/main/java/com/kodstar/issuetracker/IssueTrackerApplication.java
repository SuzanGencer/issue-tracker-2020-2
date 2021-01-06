package com.kodstar.issuetracker;

import io.jsonwebtoken.security.Keys;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;

import static com.kodstar.issuetracker.security.SecurityConstants.SECRET;

@SpringBootApplication
@EnableJpaAuditing
public class IssueTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(IssueTrackerApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecretKey secretKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));
    }


}
