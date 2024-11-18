package com.library.catalog.acceptance.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.library.DatabaseContainer;
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
class LoanResourceAcceptanceTest {

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
  void findBookById_shouldReturnBook() {
    String bookId = "d9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94";
    given()
        .contentType(ContentType.JSON)
        .when()
        .get("/books/{id}", bookId)
        .then()
        .statusCode(HttpStatus.SC_OK)
        .body("id", is(bookId))
        .body("title", is("Effective Java"))
        .body("isbn", is("9780134685991"));
  }

  // TODO POST
}
