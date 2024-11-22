package com.library.catalog.infrastructure.persistence;

import com.library.catalog.domain.*;
import com.library.catalog.domain.ports.CopyQueryPersistencePort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CopyQueryPersistenceAdapter implements CopyQueryPersistencePort {

  private final CopyRepository copyRepository;
  private final CopyEntityMapper copyEntityMapper;

  public CopyQueryPersistenceAdapter(
      CopyRepository copyRepository, CopyEntityMapper copyEntityMapper) {
    this.copyRepository = copyRepository;
    this.copyEntityMapper = copyEntityMapper;
  }

  @Override
  public Copy findById(CopyId copyId) {
    // ? TODO: add new Exception not found
    var copy = copyRepository.findById(copyId.id()).orElseThrow();
    return copyEntityMapper.toDomain(copy);
  }
}
