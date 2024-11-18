package com.library.lending.domain;

public class LoanNotFoundException extends RuntimeException {
  public LoanNotFoundException(LoanId loanId) {
    super("LoanEntity with ID " + loanId + " not found");
  }
}
