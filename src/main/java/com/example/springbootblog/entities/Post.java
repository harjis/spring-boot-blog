package com.example.springbootblog.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, List<Comment> comments) {
        this.title = title;
        this.body = body;
        this.addComments(comments);
    }

    public void addComment(Comment comment) {
        if (this.comments instanceof List) {
            this.comments.add(comment);
        } else {
            this.comments = new ArrayList<>();
            this.comments.add(comment);
        }

        comment.setPost(this);
    }

    public void addComments(List<Comment> comments) {
        for (Comment comment : comments) {
            this.addComment(comment);
        }
    }
}
