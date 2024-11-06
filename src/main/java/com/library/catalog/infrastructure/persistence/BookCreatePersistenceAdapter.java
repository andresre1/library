package com.library.catalog.infrastructure.persistence;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.ports.BookCreatePersistencePort;
import org.springframework.stereotype.Repository;

@Repository
public class BookCreatePersistenceAdapter implements BookCreatePersistencePort {

    private final BookRepository bookRepository;
    private final BookEntityMapper bookEntityMapper;

    public BookCreatePersistenceAdapter(BookRepository bookRepository, BookEntityMapper bookEntityMapper) {
        this.bookRepository = bookRepository;
        this.bookEntityMapper = bookEntityMapper;
    }

    @Override
    public Book create(Book book) {
        var bookCreated = bookRepository.save(bookEntityMapper.toEntity(book));
        return bookEntityMapper.toDomain(bookCreated);
    }
}
