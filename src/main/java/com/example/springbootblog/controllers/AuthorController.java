package com.example.springbootblog.controllers;

import com.example.springbootblog.repositories.AuthorRepository;
import com.example.springbootblog.views.authors.AuthorIndexView;
import com.example.springbootblog.views.authors.AuthorShowView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/authors")
public class AuthorController {
    private final AuthorRepository authorRepository;

    AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @GetMapping("")
    Set<AuthorShowView> index() {
        return new AuthorIndexView(authorRepository.findAll()).getAuthorShowViews();
    }
}
