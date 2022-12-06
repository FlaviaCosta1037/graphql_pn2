package com.graphqljavapn2.livros.service;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.graphqljavapn2.livros.entity.Book;
import com.graphqljavapn2.livros.repository.BookRepository;

import java.util.LinkedHashMap;
import java.util.Optional;

@Component
public class MutationAddBook implements DataFetcher<Book> {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book get(DataFetchingEnvironment environment) throws Exception {
        LinkedHashMap input = environment.getArgument("book");

        if (input.get("id") != null) {
            Long id = Long.parseLong(input.get("id").toString());
            Optional<Book> bookAd = bookRepository.findById(id);
            if (bookAd.isPresent()) {
                bookAd.get().setName(input.get("name").toString());
                bookAd.get().setPageCount(0);
                return bookRepository.save(bookAd.get());
            }
        }
        Book book = new Book();
        book.setName(input.get("name").toString());
        book.setPageCount(0);
        return bookRepository.save(book);

    }
}
