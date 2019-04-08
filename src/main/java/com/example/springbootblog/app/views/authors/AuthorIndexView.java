package com.example.springbootblog.app.views.authors;

import com.example.springbootblog.app.entities.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AuthorIndexView {
    private Set<AuthorShowView> authorShowViews;

    public AuthorIndexView(List<Author> authors) {
        this.authorShowViews = authors
                .stream()
                .map(author -> new AuthorShowView(author))
                .collect(Collectors.toSet());
    }
}
