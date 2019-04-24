package com.example.springbootblog.app.services.tag;

import com.example.springbootblog.app.entities.Tag;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
public class TagService {
    private final EntityManager entityManager;

    TagService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Tag findByTag(String tag) {
        TypedQuery<Tag> query = entityManager.createQuery(
                "select t " +
                        "from Tag t " +
                        "join fetch t.posts p " +
                        "where t.tag = :tag", Tag.class
        ).setParameter("tag", tag);

        return query.getSingleResult();
    }
}
