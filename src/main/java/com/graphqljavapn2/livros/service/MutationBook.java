package com.graphqljavapn2.livros.service;

import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.repository.BookRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MutationBook implements DataFetcher<Boolean> {
    
    @Autowired
    private BookRepository bookRepository;

    public Boolean get(DataFetchingEnvironment environment)  throws Exception{
        if(!bookRepository.findById(environment.getArgument("id")).isPresent()){
            return false;
        }
        bookRepository.deleteById(environment.getArgument("id"));
        return true;
    }
}
