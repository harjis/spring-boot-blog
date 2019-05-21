package com.example.springbootblog.app.repositories;

import com.example.springbootblog.app.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findPostByTitle(String title);
}
