package com.library;

import org.testcontainers.containers.PostgreSQLContainer;

public class DatabaseContainer extends PostgreSQLContainer<DatabaseContainer> {
  private static final String IMAGE_VERSION = "postgres:15.3-alpine";
  private static DatabaseContainer container;

  private DatabaseContainer() {
    super(IMAGE_VERSION);
  }

  public static DatabaseContainer getInstance() {
    if (container == null) {
      container =
          new DatabaseContainer()
              .withDatabaseName("springbootdevservices")
              .withUsername("dev")
              .withPassword("pass");
    }
    return container;
  }

  @Override
  public void start() {
    super.start();
  }

  @Override
  public void stop() {
    // do nothing, JVM handles shut down
  }
}
