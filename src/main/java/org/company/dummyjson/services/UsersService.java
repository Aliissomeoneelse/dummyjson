package org.company.dummyjson.services;

import lombok.RequiredArgsConstructor;
import org.company.dummyjson.dto.UserListResponse;
import org.company.dummyjson.models.Users;
import org.company.dummyjson.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {

    private final UserRepository usersRepository;

    public UserListResponse getAll(
            Integer limit,
            Integer skip,
            String sortBy,
            String order
    ) {
        List<Users> users = usersRepository.findAll();

        // SORT
        if (sortBy != null) {
            Comparator<Users> comparator = switch (sortBy) {
                case "username" -> Comparator.comparing(Users::getUsername);
                case "email" -> Comparator.comparing(Users::getEmail);
                case "firstName" -> Comparator.comparing(Users::getFirstName);
                default -> Comparator.comparing(Users::getId);
            };

            if ("desc".equalsIgnoreCase(order)) {
                comparator = comparator.reversed();
            }

            users = users.stream().sorted(comparator).toList();
        }

        int total = users.size();
        int from = Math.min(skip, total);
        int to = limit != null ? Math.min(from + limit, total) : total;

        List<Users> result = users.subList(from, to);

        return new UserListResponse(
                result,
                total,
                skip != null ? skip : 0,
                limit != null ? limit : total
        );
    }

    public Users getById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public UserListResponse search(String q) {
        List<Users> users = usersRepository.search(q);
        return new UserListResponse(users, users.size(), 0, users.size());
    }

    public Users add(Users user) {
        return usersRepository.save(user);
    }

    public Users update(Long id, Users updated) {
        Users user = getById(id);

        user.setUsername(updated.getUsername());
        user.setEmail(updated.getEmail());
        user.setFirstName(updated.getFirstName());
        user.setLastName(updated.getLastName());
        user.setGender(updated.getGender());
        user.setImage(updated.getImage());

        return usersRepository.save(user);
    }

    public void delete(Long id) {
        usersRepository.deleteById(id);
    }

}