package com.codingdojo.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;


@RestController
@RequestMapping("/api/books")
public class BooksApi {
	@Autowired
    private BookService bookService;
	
    @GetMapping("")
    public List<Book> index() {
        return bookService.allBooks();
    }
    
    @PostMapping("")
    public Book create(@RequestParam(value="title") String title, 
    					@RequestParam(value="description") String desc, 
    					@RequestParam(value="language") String lang, 
    					@RequestParam(value="numberOfPages") Integer numOfPages) {
        Book book = new Book(title, desc, lang, numOfPages);
        return bookService.createBook(book);
    }
    
    @GetMapping("/{id}")
    public Book show(@PathVariable("id") Long id) {
        Book book = bookService.findBook(id);
        return book;
    }
    
    @PutMapping("/{id}")
    public Book update(@PathVariable("id") Long id,
    					@RequestParam(value="title") String title, 
    					@RequestParam(value="description") String desc, 
    					@RequestParam(value="language") String lang, 
    					@RequestParam(value="pages") Integer numOfPages) {
        Book book = bookService.updateBook(id, title, desc, lang, numOfPages);
        return book;
    }
    
    @DeleteMapping("/{id}")
    public void destroy(@PathVariable("id") Long id) {
        bookService.deleteBook(id);
    }
}