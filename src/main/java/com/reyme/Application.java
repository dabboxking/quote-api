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

@SpringBootApplication
@Controller
public class Application {

	@Bean
	CommandLineRunner init(AuthorRepository ar, QuoteRepository qr) {

			return (evt) -> {
				createQuoteWithAuthor("Eric","Schmidt","The rise of Google, the rise of Facebook, the rise of Apple, I think are proof that there is a place for computer science as something that solves problems that people face every day.",ar,qr);
				createQuoteWithAuthor("Alan","Kay","Technology is anything that wasn’t around when you were born.",ar,qr);
				createQuoteWithAuthor("Arthur C.","Clarke","Any sufficiently advanced technology is equivalent to magic.",ar,qr);
				createQuoteWithAuthor("Mark", "Kennedy","All of the biggest technological inventions created by man – the airplane, the automobile, the computer – says little about his intelligence, but speaks volumes about his laziness.",ar,qr);
				createQuoteWithAuthor("Thomas", "Edison","Just because something doesn’t do what you planned it to do doesn’t mean it’s useless.",ar,qr);
				createQuoteWithAuthor("Albert", "Einstein","It has become appallingly obvious that our technology has exceeded our humanity.",ar,qr);
				createQuoteWithAuthor("Elbert", "Hubbard","One machine can do the work of fifty ordinary men.  No machine can do the work of one extraordinary man.",ar,qr);
				createQuoteWithAuthor("Douglas", "Adams","Technology is a word that describes something that doesn’t work yet.",ar,qr);
			};
	}

	private void createQuoteWithAuthor(String fname, String lname, String content, AuthorRepository ar, QuoteRepository qr) {
		Author author = new Author(fname,lname);
		if(author.isNew()) {
			ar.save(author);
		} else {
			author = ar.findByFullName(author.getFullName());
		}
		Quote quote = new Quote(author,content);
		qr.save(quote);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}


