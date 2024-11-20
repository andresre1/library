package com.library.catalog.infrastructure.persistence;

import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopyRepository extends CrudRepository<CopyEntity, UUID> {}
