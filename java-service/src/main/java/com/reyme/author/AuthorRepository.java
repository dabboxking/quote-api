package com.reyme.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by reyme on 8/6/16.
 */

@RepositoryRestResource
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByFullName(String fullName);
}
