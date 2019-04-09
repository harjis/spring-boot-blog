package com.example.springbootblog.app.controllers;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.services.PostCommentInserter;
import com.example.springbootblog.app.services.post.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    private final PostRepository postRepository;
    private final PostService postService;
    private final PostCommentInserter postCommentInserter;

    PostsController(PostRepository postRepository, PostService postService, PostCommentInserter postCommentInserter) {
        this.postRepository = postRepository;
        this.postService = postService;
        this.postCommentInserter = postCommentInserter;
    }

    @GetMapping("")
    List<Post> index(@RequestParam(required = false) String title, @RequestParam(required = false) String body) {
        if (title instanceof String || body instanceof String) {
            return postService.findByPostTitleAndCommentBody(title, body);
        } else {
            return postRepository.findAll();
        }
    }

    @GetMapping("/{id}")
    Post show(@PathVariable Long id, @RequestParam(required = false) String fetchType) {
        if (fetchType instanceof String && fetchType.equals("eager")) {
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
        return postService.addComment(id);
    }

    @PostMapping("/{id}/invalid_comments")
    void addInvalidComment(@PathVariable Long id) {
        postCommentInserter.createInvalidData(id);
    }
}
