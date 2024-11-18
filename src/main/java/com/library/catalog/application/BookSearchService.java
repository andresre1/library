package com.library.catalog.application;

import com.library.catalog.domain.Isbn;

public interface BookSearchService {
  BookInformation search(Isbn isbn);
}
