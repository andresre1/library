package com.library.catalog.infrastructure.persistence;

import com.library.catalog.domain.*;
import org.springframework.stereotype.Component;

@Component
public class CopyEntityMapper {

  public CopyEntity toEntity(Copy copy) {
    return new CopyEntity(
        copy.copyId().id(), copy.bookId().id(), copy.barCode().code(), copy.available());
  }

  public Copy toDomain(CopyEntity copyEntity) {
    return new Copy(
        new CopyId(copyEntity.getId()),
        new BookId(copyEntity.getBookId()),
        new BarCode(copyEntity.getBarCode()),
        copyEntity.isAvailable());
  }
}
