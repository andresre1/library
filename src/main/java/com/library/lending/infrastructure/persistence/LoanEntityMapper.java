package com.library.lending.infrastructure.persistence;

import com.library.lending.domain.CopyId;
import com.library.lending.domain.Loan;
import com.library.lending.domain.LoanId;
import com.library.lending.domain.UserId;
import org.springframework.stereotype.Component;

@Component
public class LoanEntityMapper {

  public LoanEntity toEntity(Loan loan) {
    return new LoanEntity(
        loan.loanId().id(),
        loan.copyId().id(),
        loan.userId().id(),
        loan.createAt(),
        loan.expectedReturnDate(),
        loan.returnedAt());
  }

  public Loan toDomain(LoanEntity loanEntity) {
    return new Loan(
        new LoanId(loanEntity.getLoanId()),
        new CopyId(loanEntity.getCopyId()),
        new UserId(loanEntity.getUserId()),
        loanEntity.getCreatedAt(),
        loanEntity.getExpectedReturnDate(),
        loanEntity.getReturnedAt());
  }
}
