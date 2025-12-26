package org.company.dummyjson.services;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.PostListResponse;
import org.company.dummyjson.models.Post;
import org.company.dummyjson.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    // GET ALL / LIMIT / SKIP / SORT
    public PostListResponse getAll(
            Integer limit,
            Integer skip,
            String sortBy,
            String order
    ) {
        List<Post> posts = postRepository.findAll();

        // SORT
        if (sortBy != null) {
            Comparator<Post> comparator = switch (sortBy) {
                case "title" -> Comparator.comparing(Post::getTitle);
                default -> Comparator.comparing(Post::getId);
            };

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            posts = posts.stream().sorted(comparator).toList();
        }

        int total = posts.size();
        int from = Math.min(skip, total);
        int to = limit != null ? Math.min(from + limit, total) : total;

        List<Post> result = posts.subList(from, to);

        return new PostListResponse(
                result,
                total,
                skip != null ? skip : 0,
                limit != null ? limit : total
        );
    }

    public Post getById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    public PostListResponse search(String q) {
        List<Post> posts = postRepository.search(q);
        return new PostListResponse(posts, posts.size(), 0, posts.size());
    }

    public PostListResponse getByUser(Long userId) {
        List<Post> posts = postRepository.findByUserId(userId);
        return new PostListResponse(posts, posts.size(), 0, posts.size());
    }

    public Post add(Post post) {
        return postRepository.save(post);
    }

    public Post update(Long id, Post updated) {
        Post post = getById(id);

        post.setTitle(updated.getTitle());
        post.setBody(updated.getBody());
        post.setUserId(updated.getUserId());

        return postRepository.save(post);
    }

    public void delete(Long id) {
        postRepository.deleteById(id);
    }

}