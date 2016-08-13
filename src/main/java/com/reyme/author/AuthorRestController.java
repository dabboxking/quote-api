package com.reyme.author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * Created by reyme on 8/6/16.
 */
@RestController
@RequestMapping("/authors")
public class AuthorRestController {
    final AuthorRepository authorRepository;

    @Autowired
    AuthorRestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

//    @RequestMapping(method = RequestMethod.POST)
//    ResponseEntity<?> addAuthor(@RequestBody Author input) {
//        Author result = this.authorRepository.save(new Author(input.getFirstName(),input.getLastName()));
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setLocation(ServletUriComponentsBuilder
//                .fromCurrentRequest().path("/{id}")
//                .buildAndExpand(result.getId()).toUri());
//        return new ResponseEntity<>(null,httpHeaders, HttpStatus.CREATED);
//    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Author> readAuthors() {
        return this.authorRepository.findAll();
    }
}
