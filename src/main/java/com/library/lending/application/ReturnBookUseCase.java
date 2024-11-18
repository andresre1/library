package com.library.lending.application;

import com.library.UseCase;
import com.library.lending.application.api.LoanClosed;
import com.library.lending.domain.Loan;
import com.library.lending.domain.LoanId;
import com.library.lending.domain.ports.LoanQueryPersistencePort;
import org.springframework.context.ApplicationEventPublisher;

@UseCase
public class ReturnBookUseCase {

    private final LoanQueryPersistencePort loanQueryPersistencePort;
    private final ApplicationEventPublisher eventPublisher;

    public ReturnBookUseCase(LoanQueryPersistencePort loanQueryPersistencePort, ApplicationEventPublisher eventPublisher) {
        this.loanQueryPersistencePort = loanQueryPersistencePort;
        this.eventPublisher = eventPublisher;
    }

    public Loan findById(LoanId loanId) {
        var loanEntity = loanQueryPersistencePort.findById(loanId);
        var loan = loanEntity.returned();
        eventPublisher.publishEvent(new LoanClosed(loan.copyId().id().toString()));
        return loan;
    }
}