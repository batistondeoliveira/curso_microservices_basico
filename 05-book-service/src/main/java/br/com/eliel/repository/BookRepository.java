package br.com.eliel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eliel.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
