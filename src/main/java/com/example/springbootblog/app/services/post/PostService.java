package com.example.springbootblog.app.services.post;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.exceptions.EntityNotFound;
import com.example.springbootblog.app.repositories.PostRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.List;

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

    public List<Post> findByPostTitleAndCommentBody(String title, String body) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        Root<Post> root = criteria.from(Post.class);
        if (title instanceof String && !(body instanceof String)) {
            Predicate titleWhere = builder.like(root.get("title"), title + "%");

            criteria.where(titleWhere);
        } else if (body instanceof String && !(title instanceof String)) {
            Join<Post, Comment> join = root.join("comments");
            Predicate bodyWhere = builder.like(join.get("body"), body + "%");

            criteria.where(bodyWhere);
        } else if (body instanceof String && title instanceof String) {
            Predicate titleWhere = builder.like(root.get("title"), title + "%");
            Join<Post, Comment> join = root.join("comments");
            Predicate bodyWhere = builder.like(join.get("body"), body + "%");

            criteria.where(builder.and(titleWhere, bodyWhere));
        }

        criteria.select(root).distinct(true);

        return entityManager.createQuery(criteria).getResultList();
    }
}
