package com.graphqljavapn2.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graphqljavapn2.livros.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>  {

}
