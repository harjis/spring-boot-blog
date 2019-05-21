package com.example.springbootblog.app.services.comment;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.repositories.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CommentService {
    private final CommentRepository commentRepository;

    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Comment addInvalidComment() {
        Comment comment = new Comment("Invalid Comment because it is missing Post");
        return commentRepository.save(comment);
    }
}
