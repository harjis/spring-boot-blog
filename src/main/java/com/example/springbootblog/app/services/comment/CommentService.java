package com.example.springbootblog.app.services.comment;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.repositories.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addInvalidComment() {
        Comment comment = new Comment("Invalid Comment");
        return commentRepository.save(comment);
    }
}
