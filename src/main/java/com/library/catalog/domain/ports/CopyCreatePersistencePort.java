package com.library.catalog.domain.ports;

import com.library.catalog.domain.Copy;

public interface CopyCreatePersistencePort {
    Copy create(Copy copy);
}
