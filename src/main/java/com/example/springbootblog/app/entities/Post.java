package com.example.springbootblog.app.entities;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @ManyToMany(cascade = {CascadeType.MERGE})
    @JoinTable(name = "post_tag",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

    public Post(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Post(String title, String body, Set<Comment> comments, Author author, Set<Tag> tags) {
        this.title = title;
        this.body = body;
        this.addComments(comments);
        this.setAuthor(author);
        this.addTags(tags);
    }

    public Post(String title, String body, Author author, Set<Tag> tags) {
        this.title = title;
        this.body = body;
        this.setAuthor(author);
        this.addTags(tags);
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

    public void addTag(Tag tag) {
        tags.add(tag);
        tag.getPosts().add(this);
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
        tag.getPosts().remove(this);
    }

    public void addTags(Set<Tag> tags) {
        for (Tag tag : tags) {
            this.addTag(tag);
        }
    }
}
