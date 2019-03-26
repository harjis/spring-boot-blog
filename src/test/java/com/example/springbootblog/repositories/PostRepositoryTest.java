package com.example.springbootblog.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springbootblog.entities.Comment;
import com.example.springbootblog.entities.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

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
        Post post = new Post("Title", "Body");
        entityManager.persistAndFlush(post);

        int postCount = postRepository.findAll().size();
        assertThat(postCount).isEqualTo(1);
    }

    @Test
    public void canAddComments(){
        Post post = new Post("Title", "Body");
        Comment comment = new Comment("Comment Body");
        post.addComment(comment);

        entityManager.persistAndFlush(post);

        List<Post> posts = postRepository.findAll();
        assertThat(posts.get(0)).isEqualTo(post);
        System.out.println(posts);
    }
}
