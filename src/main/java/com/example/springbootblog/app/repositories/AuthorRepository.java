package com.example.springbootblog.app.repositories;

import com.example.springbootblog.app.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
