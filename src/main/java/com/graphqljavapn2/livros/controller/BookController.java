package com.graphqljavapn2.livros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphqljavapn2.livros.entity.Book;
import com.graphqljavapn2.livros.repository.BookRepository;
import com.graphqljavapn2.livros.service.BookService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import graphql.ExecutionResult;

@RestController
@RequestMapping("/book")
public class BookController {

    private static final Logger LOGGER= LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<ExecutionResult> findAllBook(@RequestBody String query) {
        ExecutionResult er = bookService.getGraphQL().execute(query);
        return new ResponseEntity<>(er, HttpStatus.OK);
    }

    // @GetMapping
    // public List<Book> findAllBook() {
    // List<Book> findAllBook = bookRepository.findAll();
    // return findAllBook;
    // }

}
