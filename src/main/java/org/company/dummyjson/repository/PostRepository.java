package org.company.dummyjson.repository;

import org.company.dummyjson.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
                SELECT p FROM Post p
                WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :q, '%'))
                   OR LOWER(p.body) LIKE LOWER(CONCAT('%', :q, '%'))
            """)
    List<Post> search(@Param("q") String q);

    List<Post> findByUserId(Integer userId);

}