package com.example.springbootblog.views.authors;

import lombok.Data;

@Data
public class AuthorMostActiveView {
    private Long id;
    private String name;
    private Long numberOfPosts;

    public AuthorMostActiveView(Long id, String name, Long numberOfPosts) {
        this.id = id;
        this.name = name;
        this.numberOfPosts = numberOfPosts;
    }
}
