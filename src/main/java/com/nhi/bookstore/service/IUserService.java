package com.nhi.bookstore.service;

import com.nhi.bookstore.dao.SignUpRequest;
import com.nhi.bookstore.model.User;
import com.nhi.bookstore.model.UserDTO;

import java.util.List;

public interface IUserService {
    public void register(SignUpRequest signUpRequest);

    public List<UserDTO> getUsers(int enable);

    public void deleteUser(int id);

    public User acceptUser( int id);
}
