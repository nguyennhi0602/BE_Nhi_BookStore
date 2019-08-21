package com.nhi.bookstore.repositories;

import com.nhi.bookstore.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentRespository extends JpaRepository<Comment,Integer> {

    @Query(value = "SELECT * FROM comment  WHERE book_id = ?1",nativeQuery = true)
    List<Comment> findByBookID(int id);
}
