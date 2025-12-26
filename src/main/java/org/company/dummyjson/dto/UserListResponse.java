package org.company.dummyjson.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.company.dummyjson.models.Users;

import java.util.List;

@Data
@AllArgsConstructor
public class UserListResponse {

    private List<Users> users;
    private long total;
    private int skip;
    private int limit;

}