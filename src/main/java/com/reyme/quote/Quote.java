package com.reyme.quote;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.reyme.author.Author;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by reyme on 6/22/16.
 */
@Entity
public class Quote {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Author author;

    private String name;

    private String content;


    private Quote() {}

    public Quote(Author author, String content) {
        this.author = author;
        this.content = content;
        this.name = author.getFullName();
    }

    public Author getAuthor() {
        return author;
    }

    public String getName() {
        return this.name;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
