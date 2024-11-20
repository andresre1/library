package com.library;

import java.util.List;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration(proxyBeanMethods = false)
class TestcontainersConfiguration {

  @Bean
  @ServiceConnection
  PostgreSQLContainer<?> postgresContainer() {
    PostgreSQLContainer<?> postgreSQLContainer =
        new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("library_test")
            .withUsername("dev")
            .withPassword("pass");
    postgreSQLContainer.setPortBindings(List.of("30000:5432"));
    return postgreSQLContainer;
  }
}
