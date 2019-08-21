package com.nhi.bookstore.repositories;

import com.nhi.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserReponsitory extends JpaRepository<User,Integer> {
    @Query(value = "SELECT * FROM users  WHERE user_name= ?1",nativeQuery = true)
    User findByUserName(String userName);

    User findById(int id);

    //@Query(value = "SELECT * FROM users  WHERE enable= ?1",nativeQuery = true)
    List<User> getUserByEnable(int enable);
}
