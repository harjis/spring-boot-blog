package com.example.springbootblog.db;

import com.example.springbootblog.entities.Author;
import com.example.springbootblog.entities.Comment;
import com.example.springbootblog.entities.Post;
import com.example.springbootblog.repositories.AuthorRepository;
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
    CommandLineRunner initDataBase(PostRepository postRepository, AuthorRepository authorRepository) {
        return args -> {
            Author author1 = new Author("Author 1");
            Author author2 = new Author("Author 2");
            log.info("Preloading Author 1" + authorRepository.save(author1));
            log.info("Preloading Author 1" + authorRepository.save(author2));
            log.info("Preloading " + postRepository.save(
                    new Post(
                            "Title1",
                            "Body2",
                            new HashSet<>(Arrays.asList(
                                    new Comment("Comment title 1"),
                                    new Comment("Comment title 2")
                            )),
                            author1
                    )
            ));
            log.info("Preloading " + postRepository.save(
                    new Post(
                            "Title2",
                            "Body2",
                            new HashSet<>(Arrays.asList(
                                    new Comment("Comment title 3")
                            )),
                            author1
                    )
            ));

            log.info("Preloading " + postRepository.save(
                    new Post("Title3", "Body3", author2)
            ));
        };
    }
}
