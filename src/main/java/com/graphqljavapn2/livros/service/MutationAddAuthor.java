package com.graphqljavapn2.livros.service;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.entity.Author;
import com.graphqljavapn2.livros.repository.AuthorRepository;

import java.util.LinkedHashMap;
import java.util.Optional;

@Component
public class MutationAddAuthor implements DataFetcher<Author> {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public Author get(DataFetchingEnvironment environment) throws Exception {
        LinkedHashMap input = environment.getArgument("author");

        if (input.get("id") != null) {
            Long id = Long.parseLong(input.get("id").toString());
            Optional<Author> authorAd = authorRepository.findById(id);
            if (authorAd.isPresent()) {
                authorAd.get().setFirstName(input.get("firstName").toString());
                authorAd.get().setLastName(input.get("lastName").toString());
                return authorRepository.save(authorAd.get());
            }
        }
        Author author = new Author();
        author.setFirstName(input.get("name").toString());
        author.setLastName(input.get("lastName").toString());
        return authorRepository.save(author);

    }
}
