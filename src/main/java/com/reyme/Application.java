package com.reyme;

import com.reyme.author.Author;
import com.reyme.author.AuthorRepository;
import com.reyme.quote.Quote;
import com.reyme.quote.QuoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.Arrays;

@SpringBootApplication
@Controller
public class Application {

	@Bean
	CommandLineRunner init(AuthorRepository authorRepository, QuoteRepository quoteRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(
						a -> {
							quoteRepository.save(new Quote(authorRepository.save(new Author(a,a)),"I code"));
						});
	}
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


