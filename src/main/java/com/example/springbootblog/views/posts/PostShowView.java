package com.example.springbootblog.views.posts;

import com.example.springbootblog.entities.Comment;
import com.example.springbootblog.entities.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class PostShowView {
    private Long id;
    private String title;
    private String body;
    private final Set<Comment> comments = new HashSet<>();

    public PostShowView(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.body = post.getBody();
        // I don't get this. If you simply set this.comments they are not lazilly fetched
        // this.comments = post.getComments();
        this.comments.addAll(post.getComments());
    }
}
