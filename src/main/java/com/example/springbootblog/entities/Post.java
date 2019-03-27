package com.example.springbootblog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
public class Post {
    @Getter
    @Setter
    @Id
    @GeneratedValue
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private String body;

    @Getter
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private final Set<Comment> comments = new HashSet<>();

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
        this.comments.add(comment);
        comment.setPost(this);
    }

    public void addComments(Set<Comment> comments) {
        for (Comment comment : comments) {
            this.addComment(comment);
        }
    }
}
