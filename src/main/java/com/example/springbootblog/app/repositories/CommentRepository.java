package com.example.springbootblog.app.repositories;

import com.example.springbootblog.app.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
