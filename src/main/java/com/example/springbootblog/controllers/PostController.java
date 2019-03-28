package com.example.springbootblog.controllers;

import com.example.springbootblog.entities.Post;
import com.example.springbootblog.exceptions.EntityNotFound;
import com.example.springbootblog.repositories.PostRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostRepository postRepository;

    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("")
    List<Post> index() {
        List<Post> posts = postRepository.findAll();
        for (Post post : posts) {
            post.addComments(post.getComments());
        }
        return posts;
    }

    @GetMapping("/{id}")
    Post show(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }
}
