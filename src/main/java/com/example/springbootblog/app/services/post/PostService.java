package com.example.springbootblog.app.services.post;

import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.exceptions.EntityNotFound;
import com.example.springbootblog.app.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final EntityManager entityManager;

    PostService(PostRepository postRepository, EntityManager entityManager) {
        this.postRepository = postRepository;
        this.entityManager = entityManager;
    }

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }

    public Post findByIdEager(Long id) {
        TypedQuery<Post> query = entityManager.createQuery(
                "select p " +
                        "from Post p " +
                        "join fetch p.comments c " +
                        "join fetch p.author a " +
                        "where p.id = :id", Post.class
        ).setParameter("id", id);

        return query.getSingleResult();
    }
}
