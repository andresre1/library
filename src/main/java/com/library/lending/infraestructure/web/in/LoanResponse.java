package com.library.lending.infraestructure.web.in;

import com.library.lending.domain.Loan;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record LoanResponse(
    String id,
    String copyId,
    String userId,
    LocalDateTime createAt,
    LocalDate expectedReturnDate,
    LocalDateTime returnedAt) {

  public static LoanResponse from(Loan loan) {
    return new LoanResponse(
        loan.loanId().id().toString(),
        loan.copyId().id().toString(),
        loan.userId().id().toString(),
        loan.createAt(),
        loan.expectedReturnDate(),
        loan.returnedAt());
  }
}
