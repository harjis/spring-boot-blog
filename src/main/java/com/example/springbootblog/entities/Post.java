package com.example.springbootblog.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor

public class Post {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String body;

    @JsonManagedReference
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, Set<Comment> comments) {
        this.title = title;
        this.body = body;
        this.addComments(comments);
    }

    public void addComment(Comment comment) {
        if (this.comments == null) {
            this.comments = new HashSet<>();
        }
        this.comments.add(comment);
        comment.setPost(this);
    }

    public void addComments(Set<Comment> comments) {
        for (Comment comment : comments) {
            this.addComment(comment);
        }
    }
}
