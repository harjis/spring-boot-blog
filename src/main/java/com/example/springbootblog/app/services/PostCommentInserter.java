package com.example.springbootblog.app.services;

import com.example.springbootblog.app.services.comment.CommentService;
import com.example.springbootblog.app.services.post.PostService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostCommentInserter {
    private final PostService postService;
    private final CommentService commentService;

    PostCommentInserter(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    // The idea of this function is to have to services which insert some data in DB.
    // The first service is supposed to success and the second to fail.
    // The idea is to figure out on which level Transactional is supposed to be put.
    @Transactional
    public void createInvalidData(Long postId) {
        postService.addComment(postId);
        commentService.addInvalidComment();
    }
}
