package com.example.springbootblog.services.comment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.springbootblog.app.services.comment.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;


    @Test
    public void canNotSetInvalidComment() {
        assertThatThrownBy(() -> commentService.addInvalidComment(), "Comment should have post")
                .isInstanceOf(DataIntegrityViolationException.class);
    }
}
