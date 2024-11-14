package com.library.catalog.infrastructure.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "book")
public class BookEntity {
  @Id private UUID id;
  private String title;
  private String isbn;

  public BookEntity() {}

  public BookEntity(UUID id, String title, String isbn) {
    this.id = id;
    this.title = title;
    this.isbn = isbn;
  }

  public UUID getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getIsbn() {
    return isbn;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BookEntity bookEntity = (BookEntity) o;
    return Objects.equals(id, bookEntity.id);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(id);
  }
}
