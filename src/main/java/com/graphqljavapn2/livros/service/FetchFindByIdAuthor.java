package com.graphqljavapn2.livros.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.entity.Author;
import com.graphqljavapn2.livros.repository.AuthorRepository;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

@Component
public class FetchFindByIdAuthor implements DataFetcher<Author> {
    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author get(DataFetchingEnvironment environment) {
        Long id = Long.parseLong(environment.getArgument("id").toString());
        return authorRepository.findById(id).get();
    }
}
