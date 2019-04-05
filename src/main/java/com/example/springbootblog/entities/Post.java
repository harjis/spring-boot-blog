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
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private final Set<Comment> comments = new HashSet<>();

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, Set<Comment> comments, Author author) {
        this.title = title;
        this.body = body;
        this.addComments(comments);
        this.setAuthor(author);
    }

    public Post(String title, String body, Author author) {
        this.title = title;
        this.body = body;
        this.setAuthor(author);
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

    public void setAuthor(Author author) {
        this.author = author;
        author.addPost(this);
    }
}
