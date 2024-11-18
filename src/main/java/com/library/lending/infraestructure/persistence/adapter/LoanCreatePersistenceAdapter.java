package com.library.lending.infraestructure.persistence.adapter;

import com.library.lending.domain.Loan;
import com.library.lending.domain.ports.LoanCreatePersistencePort;
import com.library.lending.infraestructure.persistence.LoanEntity;
import com.library.lending.infraestructure.persistence.LoanEntityMapper;
import com.library.lending.infraestructure.persistence.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class LoanCreatePersistenceAdapter implements LoanCreatePersistencePort {

  private final LoanRepository loanRepository;
  private final LoanEntityMapper loanEntityMapper;

  public LoanCreatePersistenceAdapter(
      LoanRepository loanRepository, LoanEntityMapper loanEntityMapper) {
    this.loanRepository = loanRepository;
    this.loanEntityMapper = loanEntityMapper;
  }

  @Override
  public Loan create(Loan loan) {
    var loanEntity =
        loanRepository.save(
            new LoanEntity(
                loan.loanId().id(),
                loan.copyId().id(),
                loan.userId().id(),
                loan.createAt(),
                loan.expectedReturnDate(),
                loan.returnedAt()));
    return loanEntityMapper.toDomain(loanEntity);
  }
}
