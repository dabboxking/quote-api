package com.reyme;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Created by reyme on 6/22/16.
 */
@RestController
@RequestMapping("/quotes")
public class QuoteRestController {

    final QuoteRepository quoteRepository;

    @Autowired
    QuoteRestController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<?> addQuote(@RequestBody Quote input) {
        Quote result = quoteRepository.save(new Quote(input.getAuthor(), input.getContent()));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(result.getId()).toUri());
        return new ResponseEntity<>(null,httpHeaders, HttpStatus.CREATED);
    }

}
