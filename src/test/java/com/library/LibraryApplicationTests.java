package com.library;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.modulith.core.ApplicationModules;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class LibraryApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void verifyModules() {
		ApplicationModules.of(LibraryApplication.class).verify();
	}

}
