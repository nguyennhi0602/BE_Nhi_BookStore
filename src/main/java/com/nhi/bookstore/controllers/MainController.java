package com.nhi.bookstore.controllers;

import com.nhi.bookstore.configurations.Constants;
import com.nhi.bookstore.configurations.TokenProvider;
import com.nhi.bookstore.dao.ApiResponse;
import com.nhi.bookstore.dao.Login;
import com.nhi.bookstore.dao.SignUpRequest;
import com.nhi.bookstore.model.*;
import com.nhi.bookstore.repositories.BookRespository;
import com.nhi.bookstore.repositories.CommentRespository;
import com.nhi.bookstore.repositories.RoleReponsitory;
import com.nhi.bookstore.repositories.UserReponsitory;
import com.nhi.bookstore.serviceimpl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping
public class MainController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    UserReponsitory userRepository;

    @Autowired
    RoleReponsitory roleReponsitory;

    @Autowired
    CommentRespository commentRespository;

    @Autowired
    BookRespository bookRespository;

    private static Map<String,String> tokens=new HashMap<>();

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Login login) {
//        final Authentication authentication = authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        login.getUsername(),
//                        login.getPassword()
//                )
//        );
//        User user=userRepository.findByUserName(login.getUsername());
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//        final String token = jwtTokenUtil.generateToken(authentication);
//        tokens.put(token,login.getUsername());
//        return ResponseEntity.ok(new AuthToken(token,user.getId(),user.getRoles().size(),login.getUsername()));
//    }
//
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if(signUpRequest.getUsername()==null || signUpRequest.getPassword()==null || signUpRequest.getEmail()==null){
//            return ResponseEntity.ok(new ApiResponse(false,"You need to fill out the information "));
//        }
//        if(userRepository.findByUserName(signUpRequest.getUsername())!=null) {
//            return ResponseEntity.ok(new ApiResponse(false,"Username already exists"));
//        }
//        else {
//            User user = new User(signUpRequest.getUsername(), signUpRequest.getFirstname(), signUpRequest.getLastname(),
//                     signUpRequest.getPassword(),signUpRequest.getEmail(), 0);
//            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
//            user.setRoles(new HashSet<>());
//            user.getRoles().add(roleReponsitory.findByNameContaining(Constants.ROLE_MEMBER));
//            User result = userRepository.save(user);
//            return ResponseEntity.ok(new ApiResponse(true, "true"));
//        }
//    }
//
//    @RequestMapping("/logout")
//    public ResponseEntity<?> logout(){
//        return ResponseEntity.ok(new ApiResponse(true,"true"));
//    }

//    @RequestMapping("/getAllBook")
//    public List<Book> getAllBook(){
//        return bookService.getAllBookValid();
//    }



    @RequestMapping("/getBook/{id}")
    public Book getBookByID(@PathVariable("id") int id){
        return bookService.getBookById(id);
    }


    @RequestMapping("/getComment/{id}")
    public List<CommentDTO> getComment(@PathVariable("id") int id){
        List<Comment> comments= commentRespository.findByBookID(id);
        List<CommentDTO> commentDTOS=new ArrayList<>();
        CommentDTO commentDTO=null;
        for(Comment comment:comments){
            commentDTO=new CommentDTO();
            commentDTO.setMessage(comment.getMessage());
            commentDTO.setDayCreate(comment.getDayCreated());
            commentDTO.setDayUpdate(comment.getDayUpdated());
            commentDTO.setUser(comment.getUser().getUserName());
            commentDTOS.add(commentDTO);
        }
        return commentDTOS;
    }


    @RequestMapping("/postComment/{id}")
    public ResponseEntity<?> comment(@PathVariable("id") int id,@RequestHeader(value = "token", required=false) String token,@RequestBody Comment comment)throws IOException {
        if(token==null){
            return ResponseEntity.ok(new ApiResponse(false,""));
        }else {
            Book book=bookService.getBookById(id);
            comment.setBook(book);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date=formatter.format(calendar.getTime());
            comment.setDayCreated(date);
            comment.setDayUpdated(date);
            String userName=tokens.get(token).toString();
            User user=userRepository.findByUserName(userName);
            comment.setUser(user);
            commentRespository.save(comment);
            return ResponseEntity.ok(new ApiResponse(true,""));
        }

    }

//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteBook(@PathVariable("id") int id){
//        Book book=bookRespository.findByID(id);
//        if(book==null){
//            return ResponseEntity.ok(new ApiResponse(false,""));
//        }else{
//            bookRespository.deleteById(id);
//            return ResponseEntity.ok(new ApiResponse(true,""));
//        }
//    }



//    @RequestMapping("/editMyBook/{id}")
//    public ResponseEntity<?> editBook(@PathVariable("id") int id,@RequestBody Book book){
//        book.setEnable(0);
//        Book book1=bookRespository.findByID(id);
//        if(book1==null || book.getTitle()==""|| book.getAuthor()==""||book.getDescription()==""){
//            return ResponseEntity.ok(new ApiResponse(false,"Vui long nhap du thong tin!"));
//        }else{
//            book1.setTitle(book.getTitle());
//            book1.setAuthor(book.getAuthor());
//            book1.setDescription(book.getDescription());
//            book1.setEnable(0);
//            Calendar calendar = Calendar.getInstance();
//            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//            String date=formatter.format(calendar.getTime());
//            book1.setDayUpdate(date);
//            bookRespository.save(book1);
//            return ResponseEntity.ok(new ApiResponse(true,"Success!"));
//        }
//    }

    @RequestMapping("/createBook")
    public ResponseEntity<?> createBook(@RequestHeader(value = "token", required=false) String token,@RequestBody Book book){
        if(token==null){
            return ResponseEntity.ok(new ApiResponse(false,"You need login!!!"));
        }else{
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
            String date=formatter.format(calendar.getTime());
            book.setDayCreate(date);
            book.setDayUpdate(date);
            String userName=tokens.get(token).toString();
            User user=userRepository.findByUserName(userName);
            book.setUser(user);
            book.setEnable(0);
            bookRespository.save(book);
            return ResponseEntity.ok(new ApiResponse(true,"Success!"));
        }
    }

//    @RequestMapping("/getListBookWaiting")
//    public List<Book> getListBookWaiting(){
//        return bookRespository.getListBookWaiting();
//    }

//    @RequestMapping("/getUserWaiting")
//    public List<UserDTO> getUserWaiting(){
//       List<User> users= userRepository.getUserWaiting(0);
//       List<UserDTO> userDTOS=new ArrayList<>();
//       UserDTO userDTO=null;
//       for(User user:users){
//           userDTO=new UserDTO();
//           userDTO.setId(user.getId());
//           userDTO.setUserName(user.getUserName());
//           userDTO.setFirstName(user.getFirstName());
//           userDTO.setLastName(user.getLastName());
//           userDTO.setEmail(user.getEmail());
//           userDTOS.add(userDTO);
//       }
//       return userDTOS;
//    }
//
//    @RequestMapping("/getListUser")
//    public List<UserDTO> getListUser(){
//        List<User> users=userRepository.getListUser();
//        List<User> result=new ArrayList<>();
//        for(User user:users){
//            if(user.getRoles().size()==1){
//                result.add(user);
//            }
//        }
//        List<UserDTO> userDTOS=new ArrayList<>();
//        UserDTO userDTO=null;
//        for(User user:result){
//            userDTO=new UserDTO();
//            userDTO.setId(user.getId());
//            userDTO.setUserName(user.getUserName());
//            userDTO.setFirstName(user.getFirstName());
//            userDTO.setLastName(user.getLastName());
//            userDTO.setEmail(user.getEmail());
//            userDTOS.add(userDTO);
//        }
//        return userDTOS;
//    }

//    @RequestMapping("/acceptBook/{id}")
//    public ResponseEntity<?> acceptBook(@PathVariable("id") int id){
//        Book book=bookRespository.findByID(id);
//        book.setEnable(1);
//        bookRespository.save(book);
//        return ResponseEntity.ok(new ApiResponse(true,"Success!"));
//    }

//    @RequestMapping("/acceptUser/{id}")
//    public ResponseEntity<?> acceptUser(@PathVariable("id") int id){
//        User user=userRepository.findById(id);
//        user.setEnable(1);
//        userRepository.save(user);
//        return ResponseEntity.ok(new ApiResponse(true,"Success!"));
//    }

//    @RequestMapping("/updateUser/{id}")
//    public ResponseEntity<?> updateUser(@PathVariable("id") int id){
//        User user=userRepository.findById(id);
//        user.getRoles().add(roleReponsitory.findByNameContaining(Constants.ROLE_ADMIN));
//        userRepository.save(user);
//        return ResponseEntity.ok(new ApiResponse(true,"Success!"));
//    }

//    @DeleteMapping("/deleteUser/{id}")
//    public ResponseEntity<?> deleteUser(@PathVariable("id") int id){
//        User user=userRepository.findById(id);
//        if(user==null){
//            return ResponseEntity.ok(new ApiResponse(false,""));
//        }else{
//            userRepository.deleteById(id);
//            return ResponseEntity.ok(new ApiResponse(true,""));
//        }
//    }

}
