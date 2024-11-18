package com.library.catalog.infrastructure.web.in.rest;

import com.library.catalog.application.AddBookToCatalog;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Isbn;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookResource {

  private final SearchBookUseCase searchBookUseCase;
  private final AddBookToCatalog addBookToCatalog;

  public BookResource(SearchBookUseCase searchBookUseCase, AddBookToCatalog addBookToCatalog) {
    this.searchBookUseCase = searchBookUseCase;
    this.addBookToCatalog = addBookToCatalog;
  }

  @GetMapping("/{id}")
  public BookResponse bookById(@PathVariable String id) {
    UUID uuid = UUID.fromString(id);
    var book = searchBookUseCase.findById(new BookId(uuid));
    return BookResponse.from(book);
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.CREATED)
  public void create(@RequestBody @Valid BookCommand bookCommand) {
    addBookToCatalog.execute(new Isbn(bookCommand.isbn()));
  }
}
