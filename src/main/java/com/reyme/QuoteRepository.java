package com.reyme;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by reyme on 6/22/16.
 */
public interface QuoteRepository extends JpaRepository<Quote,Long> {
    /**
     * Find set of {@link Quote} by author
     * @param author
     * @return
     */
    Set<Quote> findByAuthor(String author);
}
