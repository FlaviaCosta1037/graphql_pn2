package com.graphqljavapn2.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.entity.Book;
import com.graphqljavapn2.livros.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class FetchFindAllBook implements DataFetcher<List<Book>> {
    
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> get(DataFetchingEnvironment environment){
        return bookRepository.findAll();
    }

}
