package com.library.lending.application;

import com.library.UseCase;
import com.library.lending.application.api.LoanCreated;
import com.library.lending.domain.CopyId;
import com.library.lending.domain.Loan;
import com.library.lending.domain.UserId;
import com.library.lending.domain.ports.LoanCreatePersistencePort;
import com.library.lending.domain.ports.LoanQueryPersistencePort;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
public class RentBookUseCase {

  private final LoanCreatePersistencePort loanCreatePersistencePort;
  private final LoanQueryPersistencePort loanQueryPersistencePort;
  private final ApplicationEventPublisher eventPublisher;

  public RentBookUseCase(
      LoanCreatePersistencePort loanCreatePersistencePort,
      LoanQueryPersistencePort loanQueryPersistencePort,
      ApplicationEventPublisher eventPublisher) {
    this.loanCreatePersistencePort = loanCreatePersistencePort;
    this.loanQueryPersistencePort = loanQueryPersistencePort;
    this.eventPublisher = eventPublisher;
  }

  public void execute(CopyId copyId, UserId userId) {
    // TODO: ensure rented copy is not rented again
    loanCreatePersistencePort.create(new Loan(copyId, userId, loanQueryPersistencePort));
    eventPublisher.publishEvent(new LoanCreated(copyId.id().toString()));
  }
}
