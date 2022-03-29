package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.model.Book;
import com.example.model.BookRepository;
import com.example.model.Category;
import com.example.model.CategoryRepository;
import com.example.model.User;
import com.example.model.UserRepository;



@SpringBootApplication
public class RikusbookstorefinalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RikusbookstorefinalApplication.class, args);
	};

	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository,
			UserRepository urepository) {
		return (args) -> {
			System.out.println("Save new repo");
			crepository.save(new Category("History"));
			crepository.save(new Category("Fiction"));
			crepository.save(new Category("Science"));
			System.out.println("Insert a book");
			System.out.println("-------------------");
			repository.save(new Book("John", "Johnson", 1993, "john@john.com", 19.99,
					crepository.findByName("History").get(0)));
			repository.save(new Book("John", "Johnson2", 1993, "john@john.com2", 19.99,
					crepository.findByName("Fiction").get(0)));
			System.out.println("");
			System.out.println("Saving users in the userlog");
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);

			System.out.println("Customers found with findAll():");
			System.out.println("-------------------------------");
			for (Book book : repository.findAll()) {
				System.out.println(book.toString());

			}

		};

	}
}
