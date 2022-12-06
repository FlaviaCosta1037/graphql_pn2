package com.graphqljavapn2.livros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.entity.Book;
import com.graphqljavapn2.livros.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class FetchFindByIdBook implements DataFetcher<Book>{
    
    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment environment) {
        Long id = Long.parseLong(environment.getArgument("id").toString());
        return bookRepository.findById(id).get();
    }
}
