package com.library.lending.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.library.lending.domain.ports.LoanQueryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class LoanTest {

  @Mock private LoanQueryPersistencePort loanQueryPersistencePort;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldThrowExceptionWhenCopyIsNull() {
    when(loanQueryPersistencePort.isAvailable(any())).thenReturn(true);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new Loan(
                    null,
                    new UserId("740980a1-f517-439d-87b1-b7b94df2de3f"),
                    loanQueryPersistencePort));

    assertEquals("copyId must not be null", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenUserIsNull() {
    when(loanQueryPersistencePort.isAvailable(any())).thenReturn(true);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new Loan(
                    new CopyId("740980a1-f517-439d-87b1-b7b94df2de3f"),
                    null,
                    loanQueryPersistencePort));

    assertEquals("userId must not be null", exception.getMessage());
  }

  @Test
  void shouldThrowExceptionWhenIsAvailableIsFalse() {
    var copyId = new CopyId("740980a1-f517-439d-87b1-b7b94df2de3f");

    when(loanQueryPersistencePort.isAvailable(any())).thenReturn(false);

    IllegalArgumentException exception =
        assertThrows(
            IllegalArgumentException.class,
            () ->
                new Loan(
                    copyId,
                    new UserId("ccde7315-e6f3-4dcf-9db0-6c00dc8502b8"),
                    loanQueryPersistencePort));

    assertEquals(
        "copy with id = " + copyId.id().toString() + " is not available", exception.getMessage());
  }
}
