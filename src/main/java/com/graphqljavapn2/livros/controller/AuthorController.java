package com.graphqljavapn2.livros.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.graphqljavapn2.livros.entity.Author;
import com.graphqljavapn2.livros.entity.Book;
import com.graphqljavapn2.livros.repository.AuthorRepository;
import com.graphqljavapn2.livros.service.AuthorService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import graphql.ExecutionResult;


@RestController
@RequestMapping("/author")
public class AuthorController {

    private static final Logger LOGGER= LoggerFactory.getLogger(AuthorController.class);
    @Autowired
    private AuthorService authorService;

    @Autowired
    private AuthorRepository authorRepository;

    @PostMapping
    public ResponseEntity<ExecutionResult> getAllBooks(@RequestBody String query){
        try{
            ExecutionResult executionResult=authorService.getGraphQL().execute(query);
            return new ResponseEntity<>(executionResult, HttpStatus.OK);
        }catch (Exception e){
            LOGGER.info("erro getAllBooks : {}", e.getMessage());
            throw new RuntimeException(new Error((e.getMessage())));
        }
    }

    @GetMapping
    public List<Author> findAllAuthor() {
    List<Author> findAllAuthor = authorRepository.findAll();
    return findAllAuthor;
    }
}
