package com.library.catalog.application;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import com.library.catalog.application.event.DomainEventListener;
import com.library.catalog.domain.BarCode;
import com.library.catalog.domain.BookId;
import com.library.catalog.domain.Copy;
import com.library.catalog.domain.CopyId;
import com.library.catalog.domain.ports.CopyCreatePersistencePort;
import com.library.catalog.domain.ports.CopyQueryPersistencePort;
import com.library.lending.application.api.LoanClosed;
import com.library.lending.application.api.LoanCreated;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DomainEventListenerTest {

  @Mock private CopyQueryPersistencePort copyQueryPersistencePort;
  @Mock private CopyCreatePersistencePort copyCreatePersistencePort;
  @InjectMocks private DomainEventListener domainEventListener;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void shouldHandleLoanCreatedEvent() {
    UUID copyId = UUID.randomUUID();
    Copy copy = new Copy(new BookId(UUID.randomUUID()), new BarCode("1234567890123"));
    copy.makeAvailable(); // Ensure initial state is available

    LoanCreated event = new LoanCreated(copyId.toString());

    when(copyQueryPersistencePort.findById(new CopyId(copyId))).thenReturn(copy);

    domainEventListener.handle(event);

    ArgumentCaptor<Copy> captor = ArgumentCaptor.forClass(Copy.class);
    verify(copyCreatePersistencePort, times(1)).create(captor.capture());

    Copy updatedCopy = captor.getValue();
    assertFalse(updatedCopy.available(), "The copy should be unavailable after LoanCreated event");
  }

  @Test
  void shouldHandleLoanClosedEvent() {
    UUID copyId = UUID.randomUUID();
    Copy copy = new Copy(new BookId(UUID.randomUUID()), new BarCode("1234567890123"));
    copy.makeUnavailable(); // Ensure initial state is unavailable

    LoanClosed event = new LoanClosed(copyId.toString());

    when(copyQueryPersistencePort.findById(new CopyId(copyId))).thenReturn(copy);

    domainEventListener.handle(event);

    ArgumentCaptor<Copy> captor = ArgumentCaptor.forClass(Copy.class);
    verify(copyCreatePersistencePort, times(1)).create(captor.capture());

    Copy updatedCopy = captor.getValue();
    assertTrue(updatedCopy.available(), "The copy should be available after LoanClosed event");
  }
}
