package com.codingdojo.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.repositories.BookRepository;

@Service
public class BookService {
    //Agregando el book al repositorio como una dependencia
    private final BookRepository bookRepository;
    
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //Devolviendo todos los libros.
    public List<Book> allBooks() {
        return bookRepository.findAll();
    }
    //Creando un libro.
    public Book createBook(Book b) {
        return bookRepository.save(b);
    }
    //Obteniendo la información de un libro
    //Explicación mía: obtener un libro encontrandolo por un id, si se encuentra, se añadirá al optionalBook
    //lo que resultará en que el if sea verdadero y retornará el objeto book, caso contrario, el optional tendrá null y retornará null.
    public Book findBook(Long id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if(optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            return null;
        }
    }
    
    public Book updateBook(Long id, String title, String desc, String lang, Integer pages) {
    	Optional<Book> idBook = bookRepository.findById(id);
    	if(idBook.isPresent()) {
    		Book updatedBook = idBook.get();
    		updatedBook.setTitle(title);
    		updatedBook.setDescription(desc);
    		updatedBook.setLanguage(lang);
    		updatedBook.setNumberOfPages(pages);
            return bookRepository.save(updatedBook);
        } else {
            return null;
        }
    }
    
    public void deleteBook(Long id) {
    	bookRepository.deleteById(id);
    }
}
