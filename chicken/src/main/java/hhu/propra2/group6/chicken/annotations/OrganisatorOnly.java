package hhu.propra2.group6.chicken.annotations;

import org.springframework.security.access.annotation.Secured;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Secured("ROLE_ORGANISATOR")
public @interface OrganisatorOnly {
}
