package com.example.springbootblog.entities;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@ToString(exclude = "post")
public class Comment {
    @Id @GeneratedValue private Long id;
    private String body;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(){}

    public Comment(String body) {
        this.body = body;
    }
}
