package com.library.catalog.application.event;

import com.library.catalog.domain.Copy;
import com.library.catalog.domain.CopyId;
import com.library.catalog.domain.ports.CopyCreatePersistencePort;
import com.library.catalog.domain.ports.CopyQueryPersistencePort;
import com.library.lending.application.api.LendingEvent;
import com.library.lending.application.api.LoanClosed;
import com.library.lending.application.api.LoanCreated;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class CopyEventListener {

  private final CopyQueryPersistencePort copyQueryPersistencePort;
  private final CopyCreatePersistencePort copyCreatePersistencePort;

  public CopyEventListener(
      CopyQueryPersistencePort copyQueryPersistencePort,
      CopyCreatePersistencePort copyCreatePersistencePort) {
    this.copyQueryPersistencePort = copyQueryPersistencePort;
    this.copyCreatePersistencePort = copyCreatePersistencePort;
  }

  @ApplicationModuleListener
  public void handle(LendingEvent event) {
    Copy copy = copyQueryPersistencePort.findById(new CopyId(event.copyId()));

    if (event instanceof LoanCreated) {
      copy = copy.makeUnavailable();
    } else if (event instanceof LoanClosed) {
      copy = copy.makeAvailable();
    }

    copyCreatePersistencePort.create(copy);
  }
}
