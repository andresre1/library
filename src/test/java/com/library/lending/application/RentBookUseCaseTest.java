package com.library.lending.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.library.lending.application.api.LoanCreated;
import com.library.lending.domain.CopyId;
import com.library.lending.domain.Loan;
import com.library.lending.domain.LoanId;
import com.library.lending.domain.UserId;
import com.library.lending.domain.ports.LoanCreatePersistencePort;
import com.library.lending.domain.ports.LoanQueryPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;

class RentBookUseCaseTest {

  @Mock private LoanCreatePersistencePort loanCreatePersistencePort;

  @Mock private LoanQueryPersistencePort loanQueryPersistencePort;

  @Mock private ApplicationEventPublisher eventPublisher;

  @InjectMocks private RentBookUseCase rentBookUseCase;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldCreateLoanAndPublishEvent() {
    CopyId copyId = new CopyId("784a6bf2-aa6b-45d9-ab5e-e19c864ff1e7");
    UserId userId = new UserId("6a9f1072-e216-488f-b097-485e7d7cb1b2");

    when(loanQueryPersistencePort.isAvailable(copyId)).thenReturn(Boolean.TRUE);

    rentBookUseCase.execute(copyId, userId);

    ArgumentCaptor<Loan> loanArgumentCaptor = ArgumentCaptor.forClass(Loan.class);
    verify(loanCreatePersistencePort, times(1)).create(loanArgumentCaptor.capture());
    Loan capturedLoan = loanArgumentCaptor.getValue();
    assert capturedLoan.copyId().equals(copyId);
    assert capturedLoan.userId().equals(userId);

    ArgumentCaptor<LoanCreated> loanCreatedArgumentCaptor =
        ArgumentCaptor.forClass(LoanCreated.class);
    verify(eventPublisher, times(1)).publishEvent(loanCreatedArgumentCaptor.capture());
    LoanCreated capturedEvent = loanCreatedArgumentCaptor.getValue();
    assert capturedEvent.copyId().equals(copyId.id().toString());
  }

  @Test
  void shouldNotCreateLoanIfAlreadyRented() {
    LoanId loanId = new LoanId("03f88826-10fb-495e-b2ef-1cb244dbb954");
    CopyId copyId = new CopyId("784a6bf2-aa6b-45d9-ab5e-e19c864ff1e7");
    UserId userId = new UserId("6a9f1072-e216-488f-b097-485e7d7cb1b2");

    when(loanQueryPersistencePort.findById(loanId))
        .thenReturn(new Loan(loanId, copyId, userId, null, null, null));

    IllegalArgumentException exception =
        assertThrows(IllegalArgumentException.class, () -> rentBookUseCase.execute(copyId, userId));

    assertEquals(
        "copy with id = " + copyId.id().toString() + " is not available", exception.getMessage());
    verify(loanCreatePersistencePort, never()).create(any(Loan.class));
    verify(eventPublisher, never()).publishEvent(any(LoanCreated.class));
  }
}
