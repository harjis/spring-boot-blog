package com.example.springbootblog.app.controllers;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.exceptions.EntityNotFound;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.views.posts.PostIndexView;
import com.example.springbootblog.app.views.posts.PostShowView;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/posts")
public class PostsController {
    private final PostRepository postRepository;

    PostsController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping("")
    List<Post> index() {
        return postRepository.findAll();
    }

    @GetMapping("/{id}")
    Post show(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }

    @GetMapping("/without_relationships/{id}")
    Post showWithoutRelationShips(@PathVariable Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }

    @PostMapping("/add_comment/{id}")
    Post addComment(@PathVariable Long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
        int numberOfComments = post.getComments().size() + 1;
        Comment comment = new Comment("Comment " + numberOfComments);
        post.addComment(comment);

        return postRepository.save(post);
    }
}
