package com.library.catalog.infrastructure.web.in.rest;

import jakarta.validation.constraints.NotBlank;

public record BookCommand(@NotBlank(message = "ISBN is required") String isbn) {}
