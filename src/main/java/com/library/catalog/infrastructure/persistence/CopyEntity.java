package com.library.catalog.infrastructure.persistence;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class CopyEntity {
  @Id private UUID id;

  @AttributeOverride(name = "id", column = @Column(name = "book_id"))
  private UUID bookId;

  private String barCode;
  private boolean available;

  public CopyEntity() {}

  public CopyEntity(UUID id, UUID bookId, String barCode, boolean available) {
    this.id = id;
    this.bookId = bookId;
    this.barCode = barCode;
    this.available = available;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getBookId() {
    return bookId;
  }

  public void setBookId(UUID bookId) {
    this.bookId = bookId;
  }

  public String getBarCode() {
    return barCode;
  }

  public void setBarCode(String barCode) {
    this.barCode = barCode;
  }

  public boolean isAvailable() {
    return available;
  }

  public void makeUnavailable() {
    this.available = false;
  }

  public void makeAvailable() {
    this.available = true;
  }
}
