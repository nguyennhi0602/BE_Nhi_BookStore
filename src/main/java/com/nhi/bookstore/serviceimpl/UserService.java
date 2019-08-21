package com.nhi.bookstore.serviceimpl;

import com.nhi.bookstore.configurations.Constants;
import com.nhi.bookstore.dao.ApiResponse;
import com.nhi.bookstore.dao.SignUpRequest;
import com.nhi.bookstore.exceptions.BadRequestException;
import com.nhi.bookstore.exceptions.NotFoundException;
import com.nhi.bookstore.model.Role;
import com.nhi.bookstore.model.User;
import com.nhi.bookstore.model.UserDTO;
import com.nhi.bookstore.repositories.RoleReponsitory;
import com.nhi.bookstore.repositories.UserReponsitory;
import com.nhi.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
public class UserService implements IUserService {
    @Autowired
    UserReponsitory userRepository;

    @Autowired
    RoleReponsitory roleReponsitory;

    @Override
    public void register(SignUpRequest signUpRequest) {
        if(signUpRequest.getUsername()==null || signUpRequest.getPassword()==null || signUpRequest.getEmail()==null){
            throw new UsernameNotFoundException("User not found");
        }
        if(userRepository.findByUserName(signUpRequest.getUsername())!=null) {
            throw new BadRequestException("Username is existed");
        }
        User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstname(), signUpRequest.getLastname(),
                signUpRequest.getPassword(),signUpRequest.getEmail(), 0);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setRoles(new HashSet<>());
        user.getRoles().add(roleReponsitory.findByNameContaining(Constants.ROLE_MEMBER));
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getUsers(int enable) {
        List<User> users= userRepository.getUserByEnable(enable);
        List<UserDTO> userDTOS=new ArrayList<>();
        UserDTO userDTO=null;
        for(User user:users){
            userDTO=new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUserName(user.getUserName());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTOS.add(userDTO);
        }
        return userDTOS;
    }

    @Override
    public void deleteUser(int id) {
        User user=userRepository.findById(id);
        if(user==null){
            throw new NotFoundException("User not found!");
        }
            userRepository.deleteById(id);
    }

    @Override
    public User acceptUser(int id) {
        User user=userRepository.findById(id);
        if(user==null){
            throw new NotFoundException("User not found");
        }
        user.setEnable(1);
        userRepository.save(user);
        return user;
    }
}
