package com.example.springbootblog.app.views.authors;

import com.example.springbootblog.app.entities.Author;
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
