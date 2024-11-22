package com.library.catalog.acceptance.rest;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.library.DatabaseContainer;
import com.library.catalog.infrastructure.web.in.rest.CopyCommand;
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
class CopyResourceAcceptanceTest {

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
  void createCopy() {
    var copyCommand = new CopyCommand("b9ecae59-dab3-4949-9b7e-e28711dcbd9e", "9781234567897");
    given()
        .contentType(ContentType.JSON)
        .body(copyCommand)
        .when()
        .post("/copies")
        .then()
        .statusCode(HttpStatus.SC_CREATED);
  }

  // TODO test exceptions
}
