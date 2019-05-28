package com.example.springbootblog.app.services.post;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class PostParams {
    private Long id;
    private String title;
    private String body;
}

