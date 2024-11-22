package com.library.lending.infrastructure.web;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.lending.application.RentBookUseCase;
import com.library.lending.application.SearchLoanUseCase;
import com.library.lending.domain.*;
import com.library.lending.infrastructure.web.in.LoanCommand;
import com.library.lending.infrastructure.web.in.LoanResource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(LoanResource.class)
class LoanResourceTest {

  @Autowired MockMvc mockMvc;

  @MockBean SearchLoanUseCase searchLoanUseCase;
  @MockBean RentBookUseCase rentBookUseCase;

  private static final ObjectMapper mapper = new ObjectMapper();

  @Test
  void getLoanByIdReturnsOkWithLoan() throws Exception {
    UUID uuid = UUID.randomUUID();
    var id = new LoanId(uuid);
    var loan =
        new Loan(
            id,
            new CopyId("a981d07a-f4d8-4739-b075-27c166d5b1a5"),
            new UserId("6dda6fc1-2de2-466f-9e55-8bd1cd98e3a8"),
            LocalDateTime.now(),
            LocalDate.now().plusDays(30),
            LocalDateTime.now());
    when(searchLoanUseCase.findById(id)).thenReturn(loan);

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/loans/{id}", uuid).contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is(loan.loanId().id().toString())))
        .andExpect(jsonPath("$.copyId", is(loan.copyId().id().toString())))
        .andExpect(jsonPath("$.userId", is(loan.userId().id().toString())));
  }

  @Test
  void notFoundBookByIdThrowException() throws Exception {
    UUID uuid = UUID.randomUUID();
    var id = new LoanId(uuid);

    when(searchLoanUseCase.findById(any())).thenThrow(new LoanNotFoundException(id));

    mockMvc
        .perform(
            MockMvcRequestBuilders.get("/books/{id}", uuid.toString())
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
  }

  @Test
  void createLoan() throws Exception {
    var loanCommand =
        new LoanCommand(
            "6dda6fc1-2de2-466f-9e55-8bd1cd98e3a8", "3db5401e-fcc1-4b17-a4cf-9afb03aeecac");
    var loanCommandValue = mapper.writeValueAsString(loanCommand);

    doNothing().when(rentBookUseCase).execute(any(), any());

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loanCommandValue)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  void createLoanArgumentCopyIdNotValid() throws Exception {
    var loanCommand = new LoanCommand("", "3db5401e-fcc1-4b17-a4cf-9afb03aeecac");
    var loanCommandValue = mapper.writeValueAsString(loanCommand);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loanCommandValue)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]", containsString("Copy id is required")));
  }

  @Test
  void createLoanArgumentUserIdNotValid() throws Exception {
    var loanCommand = new LoanCommand("3db5401e-fcc1-4b17-a4cf-9afb03aeecac", "");
    var loanCommandValue = mapper.writeValueAsString(loanCommand);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/loans")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loanCommandValue)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$[0]", containsString("User id is required")));
  }
}
