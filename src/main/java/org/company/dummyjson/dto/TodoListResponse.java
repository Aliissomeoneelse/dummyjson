package org.company.dummyjson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.dummyjson.models.Todo;

import java.util.List;

@Data
@AllArgsConstructor
public class TodoListResponse {

    private List<Todo> todos;
    private long total;
    private int skip;
    private int limit;

}