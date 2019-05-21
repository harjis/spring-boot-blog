package com.example.springbootblog.services.post;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.services.post.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostServiceTest {
    @TestConfiguration
    static class PostServiceTestContextConfiguration {
        @Bean
        public PostService postService() {
            return new PostService();
        }
    }

    @Autowired
    private PostService postService;

    @MockBean
    private PostRepository postRepository;

    @Before
    public void setUp() {
        Post post = new Post("Title", "Body");
        Post post2 = new Post("Otsikko", "Podi");

        Mockito.when(postRepository.findPostByTitle(post.getTitle())).thenReturn(post);
    }

    @Test
    public void canReturnByTitle() {
        Post fetchedPost = postService.findByTitle("Title");
        assertThat(fetchedPost.getTitle()).isEqualTo("Title");
    }
}
