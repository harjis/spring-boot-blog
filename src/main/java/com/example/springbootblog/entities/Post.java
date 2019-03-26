package com.example.springbootblog.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String body;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

    public Post() {
    }

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public void addComment(Comment comment) {
        if(this.comments instanceof List) {
            this.comments.add(comment);
        } else {
            this.comments = Arrays.asList(comment);
        }

        comment.setPost(this);
    }
}
