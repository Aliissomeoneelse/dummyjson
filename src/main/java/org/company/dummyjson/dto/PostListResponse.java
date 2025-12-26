package org.company.dummyjson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.dummyjson.models.Post;

import java.util.List;

@Data
@AllArgsConstructor
public class PostListResponse {

    private List<Post> posts;
    private long total;
    private int skip;
    private int limit;

}