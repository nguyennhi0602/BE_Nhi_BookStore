package com.nhi.bookstore.service;

import com.nhi.bookstore.model.Book;

import java.util.List;

public interface IBookService {
    public List<Book> getAllBookValid();

    public Book getBookById(int id);

    public List<Book> getListBookByUser(int userID);

    public List<Book> deleteBook(int id);


    public Book editBook(int id,Book book);

    public List<Book> getListBookIsNotAccept();

    public List<Book> acceptBook(int id);

    public List<Book> pagingBook(int pageNumber,int amountBook,String content);

    public Book createBook(Book book);

    public List<Book> deleteBookUnable(int id);

    public List<Book> searchBook(String content);
}
