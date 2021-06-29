package com.jeferson.notification;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.library.Architectures.LayeredArchitecture;
import org.junit.jupiter.api.Test;


public class ArchUnitLayersTest {

  @Test
  public void givenApplicationClasses_thenNoLayerViolationsShouldExist() {

    ImportOption ignoreTests = location -> !location.contains("/test-classes/");

    JavaClasses jc = new ClassFileImporter().withImportOption(ignoreTests)
        .importPackages("com.jeferson.notification");

    LayeredArchitecture arch = layeredArchitecture()
        .layer("api").definedBy("..api..")
//        .layer("application").definedBy("..application..")
        .layer("usecase").definedBy("..usecase..")
        .layer("impl").definedBy("..impl..")
        .layer("database").definedBy("..database..")
        .whereLayer("api").mayNotBeAccessedByAnyLayer()
        .whereLayer("usecase").mayOnlyBeAccessedByLayers("api", "database")
        .whereLayer("impl").mayNotBeAccessedByAnyLayer()
//        .whereLayer("application").mayNotBeAccessedByAnyLayer()
        .whereLayer("database").mayNotBeAccessedByAnyLayer();
    arch.check(jc);
  }
}
