package com.anyro.fligth.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anyro.fligth.springboot.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
