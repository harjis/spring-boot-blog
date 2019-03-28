package com.example.springbootblog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "post")
@ToString(exclude = "post")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String body;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    public Comment(String body) {
        this.body = body;
    }
}
