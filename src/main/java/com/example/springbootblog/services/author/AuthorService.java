package com.example.springbootblog.services.author;

import com.example.springbootblog.views.authors.AuthorMostActiveView;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AuthorService {
    private final EntityManager entityManager;

    public AuthorService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<AuthorMostActiveView> getMostActiveAuthors() {
        return entityManager.createQuery(
                "select new com.example.springbootblog.views.authors.AuthorMostActiveView(a.id, a.name, count(p) as numberOfPosts) " +
                        "from Author a " +
                        "left join a.posts p " +
                        "group by a.id, a.name " +
                        "order by numberOfPosts desc "
        ).getResultList();
    }
}
