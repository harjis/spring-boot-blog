package com.example.springbootblog.app.controllers;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.services.post.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    private final PostRepository postRepository;
    private final PostService postService;

    PostsController(PostRepository postRepository, PostService postService) {
        this.postRepository = postRepository;
        this.postService = postService;
    }

    @GetMapping("")
    List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    Post show(@PathVariable Long id, @RequestParam String fetchType) {
        if (fetchType.equals("eager")){
            return postService.findByIdEager(id);
        } else {
            return postService.findById(id);
        }
    }

    @GetMapping("/{id}/comments")
    Set<Comment> getComments(@PathVariable Long id) {
        Post post = postService.findById(id);
        return post.getComments();
    }

    @PostMapping("/{id}/comments")
    Post addComment(@PathVariable Long id) {
        Post post = postService.findById(id);
        int numberOfComments = post.getComments().size() + 1;
        Comment comment = new Comment("Comment " + numberOfComments);
        post.addComment(comment);

        return postRepository.save(post);
    }
}
