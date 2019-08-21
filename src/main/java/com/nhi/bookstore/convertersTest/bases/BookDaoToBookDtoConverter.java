//package com.nhi.bookstore.convertersTest.bases;
//
//import com.nhi.bookstore.exceptions.BadRequestException;
//import com.nhi.bookstore.model.Book;
//import com.nhi.bookstore.model.BookDTO;
//import org.springframework.stereotype.Component;
//
//@Component
//public class BookDaoToBookDtoConverter extends Converter<Book, BookDTO> {
//    @Override
//    public BookDTO convert(Book source) throws BadRequestException {
//        BookDTO bookDTO = new BookDTO();
//        bookDTO.setId(source.getId());
//        bookDTO.setName(source.getName());
//        bookDTO.setYear(source.getYear());
//
//        return bookDTO;
//    }
//}
