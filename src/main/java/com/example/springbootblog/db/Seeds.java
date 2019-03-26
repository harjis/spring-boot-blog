package com.example.springbootblog.db;

import com.example.springbootblog.entities.Post;
import com.example.springbootblog.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class Seeds {

    @Bean
    CommandLineRunner initDataBase(PostRepository postRepository) {
        return args -> {
            log.info("Preloading " + postRepository.save(new Post("Title", "Body")));
            log.info("Preloading " + postRepository.save(new Post("Title", "Body")));
        };
    }
}
