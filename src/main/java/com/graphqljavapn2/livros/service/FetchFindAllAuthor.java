package com.graphqljavapn2.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.entity.Author;
import com.graphqljavapn2.livros.repository.AuthorRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class FetchFindAllAuthor implements DataFetcher<List<Author>> {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> get(DataFetchingEnvironment environment) {
        return authorRepository.findAll();
    }

}
