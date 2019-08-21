package com.nhi.bookstore.controllers;

import com.nhi.bookstore.configurations.Constants;
import com.nhi.bookstore.configurations.TokenProvider;
import com.nhi.bookstore.dao.ApiResponse;
import com.nhi.bookstore.dao.Login;
import com.nhi.bookstore.dao.SignUpRequest;
import com.nhi.bookstore.model.AuthToken;
import com.nhi.bookstore.model.User;
import com.nhi.bookstore.model.UserDTO;
import com.nhi.bookstore.repositories.RoleReponsitory;
import com.nhi.bookstore.repositories.UserReponsitory;
import com.nhi.bookstore.service.IUserService;
import com.nhi.bookstore.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/api/users")

public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    UserReponsitory userRepository;

    @Autowired
    RoleReponsitory roleReponsitory;

    @Autowired
    IUserService iUserService=new UserService();



    @Secured("ROLE_MEMBER")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        login.getUsername(),
                        login.getPassword()
                )
        );
        User user=userRepository.findByUserName(login.getUsername());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token,user.getId(),user.getRoles().size(),login.getUsername()));
    }

    @PostMapping("/signup")
    public void registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        iUserService.register(signUpRequest);
    }

    @Secured("ROLE_MEMBER")
    @RequestMapping("/logout")
    public ResponseEntity<?> logout(){
        return ResponseEntity.ok(new ApiResponse(true,"true"));
    }

    @RequestMapping("/enable=/{idEnable}")
    public List<UserDTO> getUserUnable(@PathVariable("idEnable") int idEnable){
        return iUserService.getUsers(idEnable);
    }
    @Secured("ROLE_ADMIN")
    @RequestMapping("/delete_user/{id}")
    public void deleteUser(@PathVariable("id") int id){
       iUserService.deleteUser(id);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/accept_user/{id}")
    public User acceptUser(@PathVariable("id") int id){
        return iUserService.acceptUser(id);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/update_user/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") int id){
        User user=userRepository.findById(id);
        user.getRoles().add(roleReponsitory.findByNameContaining(Constants.ROLE_ADMIN));
        userRepository.save(user);
        return ResponseEntity.ok(new ApiResponse(true,"Success!"));
    }


}
