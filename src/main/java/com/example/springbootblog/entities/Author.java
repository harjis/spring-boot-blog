package com.example.springbootblog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "posts")
@ToString(exclude = "posts")
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private final Set<Post> posts = new HashSet<>();

    public Author(String name) {
        this.name = name;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }

    // So it seems that this sorta thing we do not want to do. Because Post has the logic
    // to "other direction" it create an infinite loop. It looks like you need to decide
    // the owning side where you want to create record from. In this case for example
    // we are most likely creating a post and we want to set currentUser to be the author
//    public void addPost(Post post) {
//        this.posts.add(post);
//        post.setAuthor(this);
//    }
//
//    public void addPosts(Set<Post> posts) {
//        for (Post post : posts) {
//            this.addPost(post);
//        }
//    }
}
