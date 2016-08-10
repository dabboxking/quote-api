package com.reyme.quote;

import com.reyme.author.Author;
import com.reyme.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Collection;
import java.util.Random;

/**
 * Created by reyme on 6/22/16.
 */
@RestController
@RequestMapping("/quotes")
public class QuoteRestController {

    final QuoteRepository quoteRepository;
    final AuthorRepository authorRepository;

    @Autowired
    QuoteRestController(AuthorRepository authorRepository,QuoteRepository quoteRepository) {
        this.authorRepository = authorRepository;
        this.quoteRepository = quoteRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addQuote(@RequestBody Quote input) {
        Author author = this.authorRepository.findByFullName(input.getName());
        Quote result = quoteRepository.save(new Quote(author, input.getContent()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null,httpHeaders, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Quote> readQuotes() {
        return this.quoteRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/random")
    ResponseEntity<Quote> readOneRandomQuote() {
        Random ran = new Random();
        int size = this.quoteRepository.findAll().size();
        Quote quote = this.quoteRepository.findAll().get(ran.nextInt(size));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccessControlAllowOrigin("*");
        return new ResponseEntity<>(quote,httpHeaders, HttpStatus.OK);
    }

}
