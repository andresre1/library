package com.library.catalog.infrastructure.web;

import com.library.catalog.application.BookResponse;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.domain.BookId;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class BookResource {

  private final SearchBookUseCase searchBookUseCase;

  public BookResource(SearchBookUseCase searchBookUseCase) {
    this.searchBookUseCase = searchBookUseCase;
  }

  @QueryMapping
  public BookResponse bookById(@Argument String id) {
    UUID uuid = UUID.fromString(id);
    return searchBookUseCase.findById(new BookId(uuid));
  }
}
