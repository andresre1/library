package com.library.catalog.web.rest;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.catalog.application.AddBookToCatalog;
import com.library.catalog.application.SearchBookUseCase;
import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.BookNotFoundException;
import com.library.catalog.domain.Isbn;
import com.library.catalog.infrastructure.web.in.rest.BookCommand;
import com.library.catalog.infrastructure.web.in.rest.BookResource;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(BookResource.class)
class BookResourceTest {

  @Autowired MockMvc mockMvc;

  @MockBean SearchBookUseCase searchBookUseCase;
  @MockBean AddBookToCatalog addBookToCatalog;

  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  void getBookByIdReturnsOkWithBook() throws Exception {
    UUID bookId = UUID.randomUUID();
    var id = new BookId(bookId);
    var book = new Book(id, "Some Title", new Isbn("978-3-16-148410-0"));
    when(searchBookUseCase.findById(id)).thenReturn(book);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/books/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(bookId.toString())))
        .andExpect(jsonPath("$.title", is("Some Title")))
        .andExpect(jsonPath("$.isbn", is("978-3-16-148410-0")));
  }

  @Test
  void notFoundBookByIdThrowException() throws Exception {
    UUID uuid = UUID.randomUUID();
    BookId bookId = new BookId(uuid);

    when(searchBookUseCase.findById(any())).thenThrow(new BookNotFoundException(bookId));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/books/{id}", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void createBook() throws Exception {
    var bookCommand = new BookCommand("9781234567897");
    var bookCommandValue = mapper.writeValueAsString(bookCommand);

    doNothing().when(addBookToCatalog).execute(any(Isbn.class));

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookCommandValue)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void createBookConstraintViolation() throws Exception {
    var bookCommand = new BookCommand("");
    var bookCommandValue = mapper.writeValueAsString(bookCommand);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(bookCommandValue)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]", containsString("ISBN is required")));
  }
}
