package com.library.catalog.infrastructure.web.in.rest;

import jakarta.validation.constraints.NotBlank;

public record CopyCommand(
    @NotBlank(message = "Book id is required") String bookId,
    @NotBlank(message = "Barcode is required") String barcode) {}
