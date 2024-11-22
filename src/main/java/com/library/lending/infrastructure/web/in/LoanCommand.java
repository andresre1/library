package com.library.lending.infrastructure.web.in;

import jakarta.validation.constraints.NotBlank;

public record LoanCommand(
    @NotBlank(message = "Copy id is required") String copyId,
    @NotBlank(message = "User id is required") String userId) {}
