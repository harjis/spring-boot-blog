package com.example.springbootblog.app.services.post;

import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostSaveService {
    private PostRepository postRepository;
    private PostParams postParams;

    public PostSaveService(PostRepository postRepository, PostParams postParams) {
        this.postRepository = postRepository;
        this.postParams = postParams;
    }

    public Post save() {
        Post post = new Post();
        post.setBody(this.postParams.getBody());
        post.setTitle(this.postParams.getTitle());
        return postRepository.save(post);
    }
}
