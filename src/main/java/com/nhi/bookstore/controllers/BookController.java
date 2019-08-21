package com.nhi.bookstore.controllers;

import com.nhi.bookstore.dao.ApiResponse;
import com.nhi.bookstore.model.Book;
import com.nhi.bookstore.service.IBookService;
import com.nhi.bookstore.serviceimpl.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    IBookService iBookService;

    @RequestMapping
    public List<Book> getAllBook() {
        return iBookService.getAllBookValid();
    }

    @Secured("ROLE_MEMBER")
    @RequestMapping("/user_id/{id}")
    public List<Book> getMyBook(@PathVariable("id") int id){
        return iBookService.getListBookByUser(id);
    }

    //@Secured("ROLE_MEMBER")
    @RequestMapping("/book_id/{id}")
    public Book getBookByID(@PathVariable("id") int id){
        return iBookService.getBookById(id);
    }

    @Secured("ROLE_MEMBER")
    @DeleteMapping("/delete_book/{id}")
    public List<Book> deleteBook(@PathVariable("id") int id){
        return iBookService.deleteBook(id);
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/delete_book_unable/{id}")
    public List<Book> deleteBookUnable(@PathVariable("id") int id){
        return iBookService.deleteBookUnable(id);
    }

    @Secured({ "ROLE_MEMBER", "ROLE_ADMIN" })
    @RequestMapping("/edit_book/{id}")
    public Book editBook(@PathVariable("id") int id, @RequestBody Book book){
        return iBookService.editBook(id,book);
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/book_unable")
    public List<Book> getListBookIsNotAccept(){
        return iBookService.getListBookIsNotAccept();
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/accept_book/{id}")
    public List<Book> acceptBook(@PathVariable("id") int id){
       return iBookService.acceptBook(id);
    }


    @RequestMapping("/paging")
    public List<Book> pagingBook(@RequestParam(value = "pageNumber",required = true) int pageNumber,@RequestParam(value = "amountBook",required = true) int amountBook,@RequestParam(value = "content",required = true) String content){
        return iBookService.pagingBook(pageNumber,amountBook,content);
    }

    @PostMapping("/create_book")
    public Book createBook(@Valid @RequestBody Book book){
        return iBookService.createBook(book);
    }

    @RequestMapping("/searchBook")
    public List<Book> searchBook(@RequestParam(name = "content") String content){
        return iBookService.searchBook(content);
    }
}
