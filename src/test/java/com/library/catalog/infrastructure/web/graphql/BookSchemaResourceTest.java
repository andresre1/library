package com.library.catalog.infrastructure.web.graphql;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.library.catalog.application.AddBookToCatalog;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Isbn;
import com.library.catalog.infrastructure.web.in.graphql.BookSchemaResource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

@GraphQlTest(BookSchemaResource.class)
class BookSchemaResourceTest {

  @Autowired private GraphQlTester graphQlTester;
  @MockBean private SearchBookUseCase searchBookUseCase;
  @MockBean private AddBookToCatalog addBookToCatalog;

  @Test
  void shouldGetFirstBook() {
    Book book = new Book("Effective Java", new Isbn("9780134685991"));

    when(searchBookUseCase.findById(any(BookId.class))).thenReturn(book);

    this.graphQlTester
        .documentName("bookDetails")
        .variable("id", "d9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94")
        .execute()
        .path("bookById")
        .matchesJson(
            """
                    {
                        "id": "%s",
                        "title": "Effective Java",
                        "isbn": "9780134685991"
                    }
                """
                .formatted(book.bookId().id().toString()));
  }
}
