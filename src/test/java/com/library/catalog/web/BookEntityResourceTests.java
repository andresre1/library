package com.library.catalog.web;

import com.library.catalog.application.BookResponse;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.infrastructure.persistence.BookEntity;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Isbn;
import com.library.catalog.infrastructure.web.BookResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@GraphQlTest(BookResource.class)
class BookEntityResourceTests {

    @Autowired
    private GraphQlTester graphQlTester;
    @MockBean
    private SearchBookUseCase searchBookUseCase;

    @Test
    void shouldGetFirstBook() {
        BookEntity bookEntity = new BookEntity("Effective Java", new Isbn("9780134685991"));
        bookEntity = new BookEntity(bookEntity.getTitle(), bookEntity.getIsbn());
        BookResponse expectedResponse = BookResponse.from(bookEntity);

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