package com.example.springbootblog.services.comment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.example.springbootblog.app.repositories.CommentRepository;
import com.example.springbootblog.app.services.comment.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentServiceTest {
    @Autowired
    private CommentRepository commentRepository;
    private CommentService commentService;

    @Before
    public void setUp() {
        commentService = new CommentService(commentRepository);
    }

    @Test
    public void canNotSetInvalidComment() {
        assertThatThrownBy(() -> {
            commentService.addInvalidComment();
            // TODO this code should throw an exception because we are trying to persist comment wihtout
            // post which is invalid. However current code does not do that. Expection is not thrown when
            // findById is called. Only when findAll is called.
//            commentRepository.findById(1L);
//            commentRepository.findAll();
        }, "Comment should have post");
    }
}
