package com.library.catalog.acceptance;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.library.DatabaseContainer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class BookEntityResourceAcceptanceTests {

//    @Container
//    @ServiceConnection
//    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:14");

    @Container
    @ServiceConnection
    static DatabaseContainer databaseContainer = DatabaseContainer.getInstance();

    @LocalServerPort
    int port;

    private HttpGraphQlTester graphQlTester;

    @BeforeEach
    void setUp() {
        WebTestClient client = WebTestClient.bindToServer()
                .baseUrl(String.format("http://localhost:%s/graphql", port))
                .build();
        graphQlTester = HttpGraphQlTester.create(client);
        assertTrue(databaseContainer.isRunning(), "postgresql is running");
        System.out.println("postgresql running in: " + databaseContainer.getJdbcUrl());
    }

    @Test
    void contextLoads() {
        assertNotNull(graphQlTester);
    }

    @Test
    void testFindBookByIdShouldReturnFirstBook() {
        this.graphQlTester
				.documentName("bookDetails")
				.variable("id", "d9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94")
                .execute()
                .path("bookById")
                .matchesJson("""
                    {
                        "id": "d9b1d7e3-54e1-48d8-9bb4-70af9d7f9f94",
                        "title": "Effective Java",
                        "isbn": "9780134685991"
                    }
                """);
    }
}