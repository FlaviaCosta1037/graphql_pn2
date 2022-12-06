package com.graphqljavapn2.livros.service;

import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.repository.AuthorRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MutationAuthor implements DataFetcher<Boolean> {
    
    @Autowired
    private AuthorRepository authorRepository;

    public Boolean get(DataFetchingEnvironment environment)  throws Exception{
        if(!authorRepository.findById(environment.getArgument("id")).isPresent()){
            return false;
        }
        authorRepository.deleteById(environment.getArgument("id"));
        return true;
    }
}
