package com.example.springbootblog.views.posts;

import com.example.springbootblog.entities.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class PostIndexView {
    private Set<PostShowView> postsShowViews;

    public PostIndexView(List<Post> posts) {
        this.postsShowViews = posts
                .stream()
                .map(post -> new PostShowView(post))
                .collect(Collectors.toSet());
    }
}
