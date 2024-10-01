package com.library.lending.application;

import com.library.UseCase;
import com.library.lending.domain.CopyId;
import com.library.lending.domain.Loan;
import com.library.lending.domain.LoanRepository;
import com.library.lending.domain.UserId;

@UseCase
public class RentBookUseCase {
    private final LoanRepository loanRepository;

    public RentBookUseCase(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public void execute(CopyId copyId, UserId userId) {
        // TODO: ensure rented copy is not rented again
        loanRepository.save(new Loan(copyId, userId, loanRepository));
    }
}