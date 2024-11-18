package com.library.lending.application.api;

public record LoanCreated(String copyId) implements LendingEvent {}
