package com.library.catalog.application;

import com.library.catalog.domain.Copy;
import com.library.catalog.domain.ports.CopyCreatePersistencePort;
import com.library.catalog.domain.ports.CopyQueryPersistencePort;
import com.library.catalog.domain.CopyId;
import com.library.lending.domain.LoanClosed;
import com.library.lending.domain.LoanCreated;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

@Component
public class DomainEventListener {

    private final CopyQueryPersistencePort copyQueryPersistencePort;
    private final CopyCreatePersistencePort copyCreatePersistencePort;

    public DomainEventListener(CopyQueryPersistencePort copyQueryPersistencePort, CopyCreatePersistencePort copyCreatePersistencePort) {
        this.copyQueryPersistencePort = copyQueryPersistencePort;
        this.copyCreatePersistencePort = copyCreatePersistencePort;
    }

    @ApplicationModuleListener
    public void handle(LoanCreated event) {
        Copy copy = copyQueryPersistencePort.findById(new CopyId(event.copyId().id()));
        copy.makeUnavailable();
        copyCreatePersistencePort.create(copy);
    }

    @ApplicationModuleListener
    public void handle(LoanClosed event) {
        Copy copy = copyQueryPersistencePort.findById(new CopyId(event.copyId().id()));
        copy.makeAvailable();
        copyCreatePersistencePort.create(copy);
    }
}