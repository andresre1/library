package com.library.catalog.infrastructure.web.in.rest;

import com.library.catalog.application.RegisterBookCopyUseCase;
import com.library.catalog.domain.BarCode;
import com.library.catalog.domain.BookId;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/copies")
public class CopyResource {

  private final RegisterBookCopyUseCase registerBookCopyUseCase;

  public CopyResource(RegisterBookCopyUseCase registerBookCopyUseCase) {
    this.registerBookCopyUseCase = registerBookCopyUseCase;
  }

  @PostMapping()
  @ResponseStatus(value = HttpStatus.CREATED)
  public void create(@RequestBody @Valid CopyCommand copyCommand) {
    registerBookCopyUseCase.execute(
        new BookId(copyCommand.bookId()), new BarCode(copyCommand.barcode()));
  }
}
