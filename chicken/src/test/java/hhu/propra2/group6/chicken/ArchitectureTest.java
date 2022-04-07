package hhu.propra2.group6.chicken;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.GeneralCodingRules;
import hhu.propra2.group6.chicken.annotations.OrganisatorOnly;
import hhu.propra2.group6.chicken.annotations.TutorOnly;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.*;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packagesOf = ChickenApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class ArchitectureTest {

    @ArchTest
    ArchRule tutorControllerisTutorOnly = classes()
            .that().resideInAPackage("..tutor..")
            .should().beAnnotatedWith(TutorOnly.class);

    @ArchTest
    ArchRule organisatorControllerIsOrganisatorOnly = classes()
            .that().resideInAPackage("..organisator..")
            .should().beAnnotatedWith(OrganisatorOnly.class);

    @ArchTest
    ArchRule studentControllerIsStudent = classes()
            .that().resideInAPackage("..web.student..")
            .should().beAnnotatedWith(Secured.class);

    @ArchTest
    ArchRule controllerShouldBeAnnotatedWithController = classes()
            .that().resideInAPackage("..controller..")
            .should().beAnnotatedWith(Controller.class);

    @ArchTest
    ArchRule noMembersShouldBeAutowired = GeneralCodingRules.NO_CLASSES_SHOULD_USE_FIELD_INJECTION;

    @ArchTest
    ArchRule fieldsInControllerShouldBePrivate = fields()
            .that().areDeclaredInClassesThat().areAnnotatedWith(Controller.class)
            .should().bePrivate();

    @ArchTest
    ArchRule onlyControllerShouldAccessConntroller = noClasses()
            .that().areNotAnnotatedWith(Controller.class)
            .should().accessClassesThat().areAnnotatedWith(Controller.class);

    @ArchTest
    ArchRule serviceShouldBeAnnotatedWithService = classes()
            .that().resideInAPackage("..service..")
            .should().beAnnotatedWith(Service.class)
            .andShould().haveSimpleNameEndingWith("Service");

    @ArchTest
    ArchRule layerTest = layeredArchitecture()
//            .layer("db").definedBy("..db..")
            .layer("controller").definedBy("..controller..")
            .layer("domain").definedBy("..domain..")
            .layer("service").definedBy("..application..")
            .whereLayer("controller").mayNotBeAccessedByAnyLayer();


    // TODO: 2022/3/24
    @ArchTest
    ArchRule onionArchitektur = onionArchitecture()
            .withOptionalLayers(true)
            .domainModels("..domain..")
            .applicationServices("..application..")
            .adapter("web", "..controller..")
            .adapter("db", "..db..");
}

