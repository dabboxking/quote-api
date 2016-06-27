package com.reyme;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@SpringBootApplication
public class QuoteApplication {

	@Bean
	CommandLineRunner init(QuoteRepository quoteRepository) {
		return (evt) -> Arrays.asList(
				"jhoeller,dsyer,pwebb,ogierke,rwinch,mfisher,mpollack,jlong".split(","))
				.forEach(
						a -> {
							quoteRepository.save(new Quote(a, "I'm " + a + " and I code."));
						});
	}

	public static void main(String[] args) {
		SpringApplication.run(QuoteApplication.class, args);
	}
}


