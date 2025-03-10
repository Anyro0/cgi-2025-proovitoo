package com.anyro.fligth.springboot.service;
import java.util.List;
import java.util.Optional;

import com.anyro.fligth.springboot.model.Book;

public interface BookService {

    List<Book> getAllBooks();

    Optional<Book> getBookById(Long id);

    Book saveBook(Book book);

    void deleteBook(Long id);
}
