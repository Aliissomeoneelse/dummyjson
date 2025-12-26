package org.company.dummyjson.services;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.TodoListResponse;
import org.company.dummyjson.models.Todo;
import org.company.dummyjson.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    public TodoListResponse getAll(Integer limit, Integer skip) {
        List<Todo> todos = todoRepository.findAll();

        int total = todos.size();
        int from = Math.min(skip, total);
        int to = limit != null ? Math.min(from + limit, total) : total;

        return new TodoListResponse(
                todos.subList(from, to),
                total,
                skip != null ? skip : 0,
                limit != null ? limit : total
        );
    }

    public Todo getById(Long id) {
        return todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Todo not found"));
    }

    public TodoListResponse getByUser(Long userId) {
        List<Todo> todos = todoRepository.findByUserId(userId);
        return new TodoListResponse(todos, todos.size(), 0, todos.size());
    }

    public Todo add(Todo todo) {
        if (todo.getCompleted() == null) {
            todo.setCompleted(false);
        }
        return todoRepository.save(todo);
    }

    public Todo update(Long id, Todo updated) {
        Todo todo = getById(id);

        todo.setTodo(updated.getTodo());
        todo.setCompleted(updated.getCompleted());
        todo.setUserId(updated.getUserId());

        return todoRepository.save(todo);
    }

    public void delete(Long id) {
        todoRepository.deleteById(id);
    }

}