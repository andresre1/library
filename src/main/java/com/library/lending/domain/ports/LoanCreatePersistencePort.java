package com.library.lending.domain.ports;

import com.library.lending.domain.Loan;

public interface LoanCreatePersistencePort {
  Loan create(Loan loan);
}
