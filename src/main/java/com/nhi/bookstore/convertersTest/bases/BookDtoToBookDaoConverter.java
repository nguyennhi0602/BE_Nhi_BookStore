//package com.nhi.bookstore.convertersTest.bases;
//
//import com.nhi.bookstore.model.Book;
//import com.nhi.bookstore.model.BookDTO;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BookDtoToBookDaoConverter extends Converter<BookDTO, Book> {
//    @Override
//    public Book convert(BookDTO source) {
//        Book book = new Book();
//
//        book.setYear(source.getYear());
//        book.setId(source.getId());
//        book.setName(source.getName());
//
//        return book;
//    }
//}
