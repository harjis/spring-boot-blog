package com.example.springbootblog.app.services.post;

import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.exceptions.EntityNotFound;
import com.example.springbootblog.app.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;

    PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }
}
