package com.example.springbootblog.services.post;

import com.example.springbootblog.app.entities.Post;
import com.example.springbootblog.app.repositories.PostRepository;
import com.example.springbootblog.app.services.post.PostParams;
import com.example.springbootblog.app.services.post.PostSaveService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostSaveServiceTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void canSave() {
        PostParams postParams = new PostParams();
        postParams.setBody("Body");
        postParams.setTitle("Title");
        PostSaveService postSaveService = new PostSaveService(postRepository, postParams);
        Post savedPost = postSaveService.save();
        Assertions.assertThat(savedPost.getTitle()).isEqualTo(postParams.getTitle());
    }
}
