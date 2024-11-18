package com.library.catalog.infrastructure.persistence;

import com.library.catalog.domain.Copy;
import com.library.catalog.domain.ports.CopyCreatePersistencePort;
import org.springframework.stereotype.Repository;

@Repository
public class CopyCreatePersistenceAdapter implements CopyCreatePersistencePort {

  private final CopyRepository copyRepository;
  private final CopyEntityMapper copyEntityMapper;

  public CopyCreatePersistenceAdapter(
      CopyRepository copyRepository, CopyEntityMapper copyEntityMapper) {
    this.copyRepository = copyRepository;
    this.copyEntityMapper = copyEntityMapper;
  }

  @Override
  public Copy create(Copy copy) {
    var copyEntity = copyRepository.save(copyEntityMapper.toEntity(copy));
    return copyEntityMapper.toDomain(copyEntity);
  }
}
