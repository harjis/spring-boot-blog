package com.example.springbootblog.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springbootblog.app.entities.Author;
import com.example.springbootblog.app.entities.Comment;
import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
    @Autowired
    TestEntityManager entityManager;

    @Autowired
    PostRepository postRepository;

    @Test
    public void canSavePost() {
        Author author = new Author("Author 1");
        Post post = new Post("Title", "Body", author);
        entityManager.persistAndFlush(post);

        int postCount = postRepository.findAll().size();
        assertThat(postCount).isEqualTo(1);
    }

    @Test
    public void canAddComment() {
        Author author = new Author("Author 1");
        Post post = new Post("Title", "Body", author);
        Comment comment = new Comment("Comment Body");
        post.addComment(comment);

        entityManager.persistAndFlush(post);

        // I would have expected this to do a select from posts and comments to get all data
        // but it only does a select from posts
        List<Post> posts = postRepository.findAll();
        Post foundPost = posts.get(0);
        assertThat(posts.size()).isEqualTo(1);
        assertThat(foundPost).isEqualTo(post);
        assertThat(foundPost.getComments().size()).isEqualTo(1);
    }

    @Test
    public void canAddComments() {
        Post post = new Post(
                "Title",
                "Body",
                new HashSet<Comment>(Arrays.asList(
                        new Comment("Comment Body 1"),
                        new Comment("Comment Body 2")
                )),
                new Author("Author")
        );

        entityManager.persistAndFlush(post);

        // I would have expected this to do a select from posts and comments to get all data
        // but it only does a select from posts
        List<Post> posts = postRepository.findAll();
        Post foundPost = posts.get(0);
        assertThat(posts.size()).isEqualTo(1);
        assertThat(foundPost).isEqualTo(post);
        assertThat(foundPost.getComments().size()).isEqualTo(2);
    }
}
