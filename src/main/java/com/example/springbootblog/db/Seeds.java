package com.example.springbootblog.db;

import com.example.springbootblog.app.entities.Author;
import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.entities.Tag;
import com.example.springbootblog.app.repositories.AuthorRepository;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.repositories.TagRepository;
import com.example.springbootblog.app.services.tag.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.HashSet;

@Configuration
@Slf4j
public class Seeds {

    @Bean
    @Transactional
    public CommandLineRunner initDataBase(
            PostRepository postRepository,
            AuthorRepository authorRepository,
            TagRepository tagRepository,
            TagService tagService
    ) {
        this.initAuthor1(postRepository, authorRepository, tagRepository);
        this.initAuthor2(postRepository, authorRepository, tagService);
        this.initAuthor3(postRepository, authorRepository);
        return args -> {
        };
    }

    void initAuthor1(PostRepository postRepository, AuthorRepository authorRepository, TagRepository tagRepository) {
        Tag tag = new Tag("Tag 1");
        Tag tag2 = new Tag("Tag 2");
        tagRepository.save(tag);
        tagRepository.save(tag2);
        Author author1 = new Author("Author 1");
        log.info("Preloading Author 1" + authorRepository.save(author1));
        log.info("Preloading " + postRepository.save(
                new Post(
                        "Title1",
                        "Body2",
                        new HashSet<>(Arrays.asList(
                                new Comment("Comment title 1"),
                                new Comment("Comment title 2")
                        )),
                        author1,
                        new HashSet<>(Arrays.asList(
                                tag,
                                tag2
                        ))
                )
        ));
        log.info("Preloading " + postRepository.save(
                new Post(
                        "Title2",
                        "Body2",
                        new HashSet<>(Arrays.asList(
                                new Comment("Comment title 3")
                        )),
                        author1,
                        new HashSet<>(Arrays.asList(
                                tag2
                        ))
                )
        ));
    }

    void initAuthor2(PostRepository postRepository, AuthorRepository authorRepository, TagService tagService) {
        // Ok I'm starting to feel the hibernate pain. I don't understand why I can't do: tagRepository.findTagByTag("Tag 1");
        // Solved:
        // 1. Another caveat of using proxies is that only public methods should be annotated with @Transactional. Methods of any other visibilities will simply ignore the annotation silently as these are not proxied. https://www.baeldung.com/transaction-configuration-with-jpa-and-spring
        // 2. We return a function in initDataBase which has the code for populating the db. Because the function is
        // executed at later time it is not in the transaction anymore. Dirty fix is to execute the code outside
        // of returned function
        Tag tag = tagService.findByTag("Tag 1");
        Author author2 = new Author("Author 2");
        log.info("Preloading Author 1" + authorRepository.save(author2));
        log.info("Preloading " + postRepository.save(
                new Post("Title3", "Body3", author2, new HashSet<>(Arrays.asList(tag)))
        ));
    }

    void initAuthor3(PostRepository postRepository, AuthorRepository authorRepository) {
        Author author = new Author("Author 3");
        log.info("Preloading Author 1" + authorRepository.save(author));
    }
}
