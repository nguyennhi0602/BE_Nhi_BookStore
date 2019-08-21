package com.nhi.bookstore.controllers;

import com.nhi.bookstore.dao.ApiResponse;
import com.nhi.bookstore.model.Book;
import com.nhi.bookstore.model.Comment;
import com.nhi.bookstore.model.CommentDTO;
import com.nhi.bookstore.model.User;
import com.nhi.bookstore.service.ICommentService;
import com.nhi.bookstore.serviceimpl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    ICommentService iCommentService=new CommentService();

    @Secured("ROLE_MEMBER")
    @RequestMapping("/book_id/{id}")
    public List<CommentDTO> getCommentsByBook(@PathVariable("id") int id){
        return iCommentService.getLisCommentByBook(id);
    }

    @Secured("ROLE_MEMBER")
    @RequestMapping("/create_comment/{id}")
    public ResponseEntity<?> createComment(@PathVariable("id") int id,@Valid @RequestBody Comment comment){
       iCommentService.createComment(id,comment);
       return ResponseEntity.ok(new ApiResponse(true,""));
    }

}
