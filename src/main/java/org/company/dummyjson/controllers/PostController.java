package org.company.dummyjson.controllers;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.PostListResponse;
import org.company.dummyjson.models.Post;
import org.company.dummyjson.services.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    // GET ALL / LIMIT / SKIP / SORT
    @GetMapping
    public PostListResponse getAll(
            @RequestParam(required = false) Integer limit,
            @RequestParam(defaultValue = "0") Integer skip,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order
    ) {
        return postService.getAll(limit, skip, sortBy, order);
    }

    // GET SINGLE
    @GetMapping("/{id}")
    public Post getOne(@PathVariable Long id) {
        return postService.getById(id);
    }

    // SEARCH
    @GetMapping("/search")
    public PostListResponse search(@RequestParam String q) {
        return postService.search(q);
    }

    // POSTS BY USER
    @GetMapping("/user/{userId}")
    public PostListResponse byUser(@PathVariable Integer userId) {
        return postService.getByUser(userId);
    }

    // ADD
    @PostMapping("/add")
    public Post add(@RequestBody Post post) {
        return postService.add(post);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Post update(
            @PathVariable Long id,
            @RequestBody Post post
    ) {
        return postService.update(id, post);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        postService.delete(id);
    }

}