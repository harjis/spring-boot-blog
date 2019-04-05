package com.example.springbootblog.controllers;

import com.example.springbootblog.entities.Post;
import com.example.springbootblog.exceptions.EntityNotFound;
import com.example.springbootblog.repositories.PostRepository;
import com.example.springbootblog.views.posts.PostIndexView;
import com.example.springbootblog.views.posts.PostShowView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
    private final PostRepository postRepository;

    PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("")
    Set<PostShowView> index() {
        return new PostIndexView(postRepository.findAll()).getPostsShowViews();
    }

    @GetMapping("/{id}")
    PostShowView show(@PathVariable Long id) {
        return new PostShowView(postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id)));
    }

    @GetMapping("/without_comments/{id}")
    Post showWithoutComments(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }
}
