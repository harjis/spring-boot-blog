package com.example.springbootblog.app.repositories;

import com.example.springbootblog.app.entities.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findTagByTag(String Tag);
}
