package com.library.lending.infraestructure.web.in;

import com.library.lending.application.RentBookUseCase;
import com.library.lending.application.ReturnBookUseCase;
import com.library.lending.domain.CopyId;
import com.library.lending.domain.LoanId;
import com.library.lending.domain.UserId;
import jakarta.validation.Valid;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loans")
public class LoanResource {

  private final ReturnBookUseCase returnBookUseCase;
  private final RentBookUseCase rentBookUseCase;

  public LoanResource(ReturnBookUseCase returnBookUseCase, RentBookUseCase rentBookUseCase) {
    this.returnBookUseCase = returnBookUseCase;
    this.rentBookUseCase = rentBookUseCase;
  }

  @GetMapping("/{id}")
  public LoanResponse loanById(@PathVariable String id) {
    UUID uuid = UUID.fromString(id);
    var loan = returnBookUseCase.findById(new LoanId(uuid));
    return LoanResponse.from(loan);
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.CREATED)
  public void create(@RequestBody @Valid LoanCommand loanCommand) {
    rentBookUseCase.execute(
        new CopyId(UUID.fromString(loanCommand.copyId())),
        new UserId(UUID.fromString(loanCommand.userId())));
  }
}
