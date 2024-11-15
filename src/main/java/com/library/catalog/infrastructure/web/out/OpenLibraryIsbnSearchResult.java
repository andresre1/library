package com.library.catalog.infrastructure.web.out;

import java.util.List;

public record OpenLibraryIsbnSearchResult(List<String> publishers,
                                          String title,
                                          List<String> isbn_13,
                                          int revisions) {
}