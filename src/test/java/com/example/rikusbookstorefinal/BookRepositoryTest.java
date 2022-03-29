package com.example.rikusbookstorefinal;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.model.Book;
import com.example.model.BookRepository;
import com.example.model.Category;

import org.junit.jupiter.api.Test;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookRepositoryTest {

	@Autowired
	private BookRepository repository;

	@Test
	public void createNewBook() {
		Book book = new Book("John", "Johnson", 2, "A", 123123, new Category("BITE"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}

	@Test
	public void findByNameShouldReturnAuthor() {
		List<Book> books = repository.findByTitle("John");

		assertThat(books).hasSize(2);
		assertThat(books.get(0).getAuthor()).isEqualTo("Johnson");
	}

	@Test
	public void deleteNewBook() {
		List<Book> books = repository.findByTitle("John");
		Book booktodelete = books.get(0);
		repository.delete(booktodelete);
		List<Book> newBooks = repository.findByTitle("John");
		assertThat(newBooks).hasSize(1);
	}

}