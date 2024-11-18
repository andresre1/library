package com.library.lending.infraestructure.persistence.adapter;

import com.library.lending.domain.CopyId;
import com.library.lending.domain.Loan;
import com.library.lending.domain.LoanId;
import com.library.lending.domain.LoanNotFoundException;
import com.library.lending.domain.ports.LoanQueryPersistencePort;
import com.library.lending.infraestructure.persistence.LoanEntityMapper;
import com.library.lending.infraestructure.persistence.LoanRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LoanQueryPersistenceAdapter implements LoanQueryPersistencePort {

  private final LoanRepository loanRepository;
  private final LoanEntityMapper loanEntityMapper;

  public LoanQueryPersistenceAdapter(
      LoanRepository loanRepository, LoanEntityMapper loanEntityMapper) {
    this.loanRepository = loanRepository;
    this.loanEntityMapper = loanEntityMapper;
  }

  @Override
  public Loan findById(LoanId loanId) {
    var loanEntity =
        loanRepository.findById(loanId.id()).orElseThrow(() -> new LoanNotFoundException(loanId));
    return loanEntityMapper.toDomain(loanEntity);
  }

  @Override
  public boolean isAvailable(CopyId copyId) {
    return loanRepository.isAvailable(copyId.id());
  }
}
