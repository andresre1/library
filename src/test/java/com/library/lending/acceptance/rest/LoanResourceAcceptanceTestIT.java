package com.library.lending.acceptance.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.library.DatabaseContainer;
import com.library.lending.infrastructure.web.in.LoanCommand;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class LoanResourceAcceptanceTestIT {

  @Container @ServiceConnection
  static DatabaseContainer databaseContainer = DatabaseContainer.getInstance();

  @LocalServerPort private Integer port;

  @BeforeEach
  void setUp() {
    RestAssured.port = port;
    assertTrue(databaseContainer.isRunning(), "postgresql is running");
    System.out.println("postgresql running in: " + databaseContainer.getJdbcUrl());
  }

  @Test
  void findLoanById_shouldReturnLoan() {
    String loanId = "f3a7e812-3d9b-4f3a-a1b7-9bf5d7d8f9a1";
    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/loans/{id}", loanId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("id", is(loanId))
        .body("copyId", is("e1a7f82c-cb4f-4a42-8a2f-50a76d7a0348"))
        .body("userId", is("a8c12345-f7d9-48a8-b7f9-98a76d3a047b"));
  }

  @Test
  void createLoan() {
    var loanCommand = new LoanCommand("5e809384-b046-48e1-8c5b-b2884b28d337", "aca308e6-d220-414e-ad12-7e2c233889f0");
    given()
        .contentType(ContentType.JSON)
        .body(loanCommand)
        .when()
        .post("/loans")
        .then()
        .statusCode(HttpStatus.SC_CREATED);
  }

  // TODO test exceptions
}
