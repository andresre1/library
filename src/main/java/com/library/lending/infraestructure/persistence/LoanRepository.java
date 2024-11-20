package com.library.lending.infraestructure.persistence;

import java.util.UUID;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, UUID> {
  @Query("select count(*) = 0 from LoanEntity where copyId = :id and returnedAt is null")
  boolean isAvailable(UUID id);
}
