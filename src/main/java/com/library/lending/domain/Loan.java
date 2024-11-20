package com.library.lending.domain;

import com.library.lending.domain.ports.LoanQueryPersistencePort;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.util.Assert;

public record Loan(
    LoanId loanId,
    CopyId copyId,
    UserId userId,
    LocalDateTime createAt,
    LocalDate expectedReturnDate,
    LocalDateTime returnedAt) {

  public Loan(CopyId copyId, UserId userId, LoanQueryPersistencePort loanQueryPersistencePort) {
    this(new LoanId(), copyId, userId, LocalDateTime.now(), LocalDate.now().plusDays(30), null);
    Assert.notNull(copyId, "copyId must not be null");
    Assert.notNull(userId, "userId must not be null");
    Assert.isTrue(
        loanQueryPersistencePort.isAvailable(copyId),
        "copy with id = " + copyId.id().toString() + " is not available");
  }

  public Loan returned() {
    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(expectedReturnDate.atStartOfDay())) {
      // ! TODO: Calcular la multa si la devolución es después de la fecha esperada
    }
    return new Loan(loanId, copyId, userId, createAt, expectedReturnDate, now);
  }
}
