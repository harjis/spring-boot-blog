package com.example.springbootblog.app.services.post;

import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.exceptions.EntityNotFound;
import com.example.springbootblog.app.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private EntityManager entityManager;

    public Post findById(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new EntityNotFound(id));
    }

    public Post findByTitle(String title){
        return postRepository.findPostByTitle(title);
    }

    public Post findByIdEager(Long id) {
        TypedQuery<Post> query = entityManager.createQuery(
                "select p " +
                        "from Post p " +
                        "join fetch p.comments c " +
                        "join fetch p.author a " +
                        "join fetch p.tags t " +
                        "where p.id = :id", Post.class
        ).setParameter("id", id);

        return query.getSingleResult();
    }

    public List<Post> findByPostTitleAndCommentBody(String title, String body) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Post> criteria = builder.createQuery(Post.class);
        Root<Post> root = criteria.from(Post.class);
        List<Predicate> predicates = new ArrayList<>();
        if (title instanceof String) {
            Predicate titleWhere = builder.like(root.get("title"), title + "%");
            predicates.add(titleWhere);
        }

        if (body instanceof String) {
            Join<Post, Comment> join = root.join("comments");
            Predicate bodyWhere = builder.like(join.get("body"), body + "%");
            predicates.add(bodyWhere);
        }

        // I don't get why I can't just give predicates here :<
        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        criteria.select(root).distinct(true);

        return entityManager.createQuery(criteria).getResultList();
    }

    public Post addComment(Long id) {
        Post post = findById(id);
        int numberOfComments = post.getComments().size() + 1;
        Comment comment = new Comment("Comment " + numberOfComments);
        post.addComment(comment);

        return postRepository.save(post);
    }
}
