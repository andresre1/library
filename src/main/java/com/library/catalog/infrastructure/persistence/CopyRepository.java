package com.library.catalog.infrastructure.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CopyRepository extends CrudRepository<CopyEntity, UUID> {
}