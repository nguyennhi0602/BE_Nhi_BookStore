package com.nhi.bookstore.service;

import com.nhi.bookstore.model.Comment;
import com.nhi.bookstore.model.CommentDTO;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ICommentService {
    public List<CommentDTO> getLisCommentByBook(int id);

    public void createComment(int id,Comment comment);
}
