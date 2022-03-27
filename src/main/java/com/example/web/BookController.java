package com.example.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.model.Book;
import com.example.model.BookRepository;
import com.example.model.CategoryRepository;

@Controller
public class BookController {

	// Repositories defined below
	@Autowired
	private BookRepository repository;

	@Autowired
	private CategoryRepository crepository;
	
	
	//Method for login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}    
	
	
	// Show all books
	@RequestMapping("/list")
	public String listBooks(Model model) {
		model.addAttribute("listofbooks", repository.findAll());
		return "booklist";
	}

	// RESTful get all books
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public @ResponseBody List<Book> BookListRest() {
		return (List<Book>) repository.findAll();
	}

	// RESTful service to get book by ID
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long id) {
		return repository.findById(id);
	}

	@RequestMapping("/add")
	public String addBook(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("categories", crepository.findAll());
		return "addbook";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(Book book) {
		repository.save(book);
		return "redirect:list";
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteBook(@PathVariable("id") Long id, Model model) {
		repository.deleteById(id);
		return "redirect:../list";
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editBook(@PathVariable("id") Long id, Model model) {
		model.addAttribute("book", repository.findById(id));
		model.addAttribute("categories", crepository.findAll());
		return "editbook";
	}

	@RequestMapping(value = "/process", method = RequestMethod.POST)
	public String saveBook(@ModelAttribute Book book) {
		repository.save(book);

		return "redirect:/list";
	}

}
