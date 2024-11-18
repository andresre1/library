package com.library;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

class ModularityTests {

  ApplicationModules modules = ApplicationModules.of(LibraryApplication.class);

  @Test
  void verifyModularity() {

    // --> Module model
    System.out.println(modules.toString());

    // --> Trigger verification
    modules.verify();
  }

  @Test
  void renderDocumentation() throws Exception {

    var canvasOptions = Documenter.CanvasOptions.defaults();

    // --> Optionally enable linking of JavaDoc
    // .withApiBase("https://foobar.something")

    var docOptions =
        Documenter.DiagramOptions.defaults().withStyle(Documenter.DiagramOptions.DiagramStyle.UML);

    new Documenter(modules).writeDocumentation(docOptions, canvasOptions);
  }
}
