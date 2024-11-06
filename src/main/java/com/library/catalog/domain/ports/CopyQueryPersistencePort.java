package com.library.catalog.domain.ports;

import com.library.catalog.domain.Book;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Copy;
import com.library.catalog.domain.CopyId;

public interface CopyQueryPersistencePort {
    Copy findById(CopyId copyId);
}
