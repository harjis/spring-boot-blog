package com.example.springbootblog.db;

import com.example.springbootblog.entities.Comment;
import com.example.springbootblog.entities.Post;
import com.example.springbootblog.repositories.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Slf4j
public class Seeds {

    @Bean
    CommandLineRunner initDataBase(PostRepository postRepository) {
        return args -> {

            log.info("Preloading " + postRepository.save(
                    new Post(
                            "Title1",
                            "Body2",
                            new HashSet<>(Arrays.asList(
                                    new Comment("Comment title 1"),
                                    new Comment("Comment title 2")
                            ))
                    )
            ));
            log.info("Preloading " + postRepository.save(
                    new Post(
                            "Title2",
                            "Body2",
                            new HashSet<>(Arrays.asList(
                                    new Comment("Comment title 3")
                            ))
                    )
            ));
        };
    }
}
