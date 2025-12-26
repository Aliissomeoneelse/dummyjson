package org.company.dummyjson.controllers;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.TodoListResponse;
import org.company.dummyjson.models.Todo;
import org.company.dummyjson.services.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public TodoListResponse getAll(
            @RequestParam(required = false) Integer limit,
            @RequestParam(defaultValue = "0") Integer skip
    ) {
        return todoService.getAll(limit, skip);
    }

    // GET /todos/1
    @GetMapping("/{id}")
    public Todo getById(@PathVariable Long id) {
        return todoService.getById(id);
    }

    // GET /todos/user/5
    @GetMapping("/user/{userId}")
    public TodoListResponse getByUser(@PathVariable Long userId) {
        return todoService.getByUser(userId);
    }

    // POST /todos/add
    @PostMapping("/add")
    public Todo add(@RequestBody Todo todo) {
        return todoService.add(todo);
    }

    // PUT /todos/1
    @PutMapping("/{id}")
    public Todo update(@PathVariable Long id, @RequestBody Todo todo) {
        return todoService.update(id, todo);
    }

    // DELETE /todos/1
    @DeleteMapping("/{id}")
    public Map<String, Object> delete(@PathVariable Long id) {
        todoService.delete(id);
        return Map.of(
                "isDeleted", true,
                "id", id
        );
    }

}