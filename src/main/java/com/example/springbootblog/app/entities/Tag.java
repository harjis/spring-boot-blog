package com.example.springbootblog.app.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = "posts")
@ToString(exclude = "posts")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;

    @NaturalId
    private String tag;

    @JsonBackReference
    @ManyToMany(mappedBy = "tags")
    private Set<Post> posts = new HashSet<>();

    public Tag(String tag) {
        this.tag = tag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(tag);
    }
}
