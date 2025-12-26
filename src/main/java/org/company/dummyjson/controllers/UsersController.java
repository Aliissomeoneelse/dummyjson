package org.company.dummyjson.controllers;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.UserListResponse;
import org.company.dummyjson.models.Users;
import org.company.dummyjson.services.UsersService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    // GET ALL / LIMIT / SKIP / SORT
    @GetMapping
    public UserListResponse getAll(
            @RequestParam(required = false) Integer limit,
            @RequestParam(defaultValue = "0") Integer skip,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String order
    ) {
        return usersService.getAll(limit, skip, sortBy, order);
    }

    // GET SINGLE
    @GetMapping("/{id}")
    public Users getOne(@PathVariable Long id) {
        return usersService.getById(id);
    }

    // SEARCH
    @GetMapping("/search")
    public UserListResponse search(@RequestParam String q) {
        return usersService.search(q);
    }

    // ADD
    @PostMapping
    public Users add(@RequestBody Users user) {
        return usersService.add(user);
    }

    // UPDATE
    @PutMapping("/{id}")
    public Users update(
            @PathVariable Long id,
            @RequestBody Users user
    ) {
        return usersService.update(id, user);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        usersService.delete(id);
    }

}