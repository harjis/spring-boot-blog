package com.example.springbootblog.app.controllers;

import com.example.springbootblog.app.repositories.AuthorRepository;
import com.example.springbootblog.app.services.author.AuthorService;
import com.example.springbootblog.app.views.authors.AuthorIndexView;
import com.example.springbootblog.app.views.authors.AuthorMostActiveView;
import com.example.springbootblog.app.views.authors.AuthorShowView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;
    private final AuthorService authorService;

    AuthorController(AuthorRepository authorRepository, AuthorService authorService) {
        this.authorRepository = authorRepository;
        this.authorService = authorService;
    }

    @GetMapping("")
    Set<AuthorShowView> index() {
        return new AuthorIndexView(authorRepository.findAll()).getAuthorShowViews();
    }

    @GetMapping("/most_active")
    List<AuthorMostActiveView> mostActive() {
        return authorService.getMostActiveAuthors();
    }
}
