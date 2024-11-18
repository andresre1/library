package com.library.lending.domain.ports;

import com.library.lending.domain.CopyId;
import com.library.lending.domain.Loan;
import com.library.lending.domain.LoanId;

public interface LoanQueryPersistencePort {
  Loan findById(LoanId loanId);

  boolean isAvailable(CopyId copyId);
}
