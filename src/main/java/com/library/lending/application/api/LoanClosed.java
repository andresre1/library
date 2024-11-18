package com.library.lending.application.api;

public record LoanClosed(String copyId) implements LendingEvent {}
