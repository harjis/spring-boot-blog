package com.example.springbootblog.views.authors;

import com.example.springbootblog.entities.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthorShowView {
    private Long id;
    private String name;

    public AuthorShowView(Author author) {
        this.id = author.getId();
        this.name = author.getName();
    }
}
