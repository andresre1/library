package com.library.catalog.web;

import com.library.catalog.application.BookResponse;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Isbn;
import com.library.catalog.infrastructure.web.BookResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@GraphQlTest(BookResource.class)
class BookResourceTests {

    @Autowired
    private GraphQlTester graphQlTester;
    @MockBean
    private SearchBookUseCase searchBookUseCase;

    @Test
    void shouldGetFirstBook() {
        Book book = new Book("Effective Java", new Isbn("9780134685991"));
        book = new Book(book.getTitle(), book.getIsbn());
        BookResponse expectedResponse = BookResponse.from(book);

        when(searchBookUseCase.findById(any(BookId.class))).thenReturn(expectedResponse);

        this.graphQlTester
				.documentName("bookDetails")
				.variable("id", "d9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94")
                .execute()
                .path("bookById")
                .matchesJson("""
                    {
                        "id": "%s",
                        "title": "Effective Java",
                        "isbn": "9780134685991"
                    }
                """.formatted(expectedResponse.id()));
    }
}