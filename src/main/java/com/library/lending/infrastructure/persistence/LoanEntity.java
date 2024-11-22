package com.library.lending.infrastructure.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "loan")
public class LoanEntity {
  @Id private UUID loanId;

  @AttributeOverride(name = "id", column = @Column(name = "copy_id"))
  private UUID copyId;

  @AttributeOverride(name = "id", column = @Column(name = "user_id"))
  private UUID userId;

  private LocalDateTime createdAt;
  private LocalDate expectedReturnDate;
  private LocalDateTime returnedAt;

  @Version private Long version;

  public LoanEntity() {}

  public LoanEntity(
      UUID loanId,
      UUID copyId,
      UUID userId,
      LocalDateTime createdAt,
      LocalDate expectedReturnDate,
      LocalDateTime returnedAt) {
    this.loanId = loanId;
    this.copyId = copyId;
    this.userId = userId;
    this.createdAt = createdAt;
    this.expectedReturnDate = expectedReturnDate;
    this.returnedAt = returnedAt;
  }

  public UUID getLoanId() {
    return loanId;
  }

  public UUID getCopyId() {
    return copyId;
  }

  public UUID getUserId() {
    return userId;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDate getExpectedReturnDate() {
    return expectedReturnDate;
  }

  public LocalDateTime getReturnedAt() {
    return returnedAt;
  }

  public Long getVersion() {
    return version;
  }
}
