package com.library.catalog.infrastructure.web.out;

import com.library.catalog.application.BookInformation;
import com.library.catalog.application.BookSearchService;
import com.library.catalog.domain.Isbn;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
public class OpenLibraryBookSearchService implements BookSearchService {
  private final RestClient restClient;

  public OpenLibraryBookSearchService(RestClient.Builder builder) {
    this.restClient = builder.baseUrl("https://openlibrary.org/").build();
  }

  public BookInformation search(Isbn isbn) {
    OpenLibraryIsbnSearchResult result =
        restClient
            .get()
            .uri("isbn/{isbn}.json", isbn.value())
            .retrieve()
            .body(OpenLibraryIsbnSearchResult.class);
    // ! TODO: check null result
    return new BookInformation(result.title());
  }
}
