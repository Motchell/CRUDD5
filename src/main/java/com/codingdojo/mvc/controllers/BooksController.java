package com.codingdojo.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.codingdojo.mvc.models.Book;
import com.codingdojo.mvc.services.BookService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {
	@Autowired
    private BookService bookService;

    @GetMapping("/")
    public String index(Model model) {
        List<Book> books = bookService.allBooks();
        model.addAttribute("books", books);
        return "/books/index";
    }
    
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "/books/new";
    }
    
    @PostMapping("/books")
    public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
        if (result.hasErrors()) {
            return "/books/new";
        } else {
            bookService.createBook(book);
            return "redirect:/";
        }
    }
    
    @GetMapping("/{id}")
    public String showBook(Model model,
			  				@PathVariable("id") Long id) {
    	Book findedBook = bookService.findBook(id);
    	model.addAttribute(findedBook);
		return "/books/show";
    }
}