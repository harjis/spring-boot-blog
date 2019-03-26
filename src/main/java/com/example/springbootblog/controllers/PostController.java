package com.example.springbootblog.controllers;

import com.example.springbootblog.entities.Post;
import com.example.springbootblog.repositories.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {
    private final PostRepository postRepository;

    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("/posts")
    List<Post> index() {
        return postRepository.findAll();
    }
}
