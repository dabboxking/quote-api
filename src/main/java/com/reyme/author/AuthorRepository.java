package com.reyme.author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by reyme on 8/6/16.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {
    Author findByFullName(String fullName);
}
