package com.example.springbootblog.app.controllers;

import com.example.springbootblog.app.exceptions.EntityNotFound;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.views.posts.PostIndexView;
import com.example.springbootblog.app.views.posts.PostShowView;
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
