package com.library.lending.domain;

import java.util.UUID;
import org.springframework.util.Assert;

public record CopyId(UUID id) {

    public CopyId {
        Assert.notNull(id, "id must not be null");
    }

    public CopyId(String id) {
        this(UUID.fromString(id));
    }

    public CopyId() {
        this(UUID.randomUUID());
    }
}