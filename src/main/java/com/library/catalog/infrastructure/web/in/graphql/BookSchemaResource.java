package com.library.catalog.infrastructure.web.in.graphql;

import com.library.catalog.application.BookResponse;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.domain.BookId;
import java.util.UUID;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;


@Controller
public class BookSchemaResource {

  private final SearchBookUseCase searchBookUseCase;

  public BookSchemaResource(SearchBookUseCase searchBookUseCase) {
    this.searchBookUseCase = searchBookUseCase;
  }

  @QueryMapping
  public BookResponse bookById(@Argument String id) {
    UUID uuid = UUID.fromString(id);
    return searchBookUseCase.findById(new BookId(uuid));
  }
}
