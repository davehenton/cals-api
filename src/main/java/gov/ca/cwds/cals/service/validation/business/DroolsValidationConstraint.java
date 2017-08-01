package gov.ca.cwds.cals.service.validation.business;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import gov.ca.cwds.cals.service.validation.business.configuration.DroolsValidationConfigurationRegistry;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Payload;

/**
 * @author CWDS CALS API Team
 */

@Target({FIELD, PARAMETER, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
public @interface DroolsValidationConstraint {

  DroolsValidationConfigurationRegistry validationConfiguration();

  String validationSession() default "inProgressValidationSession";

  String message() default "";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}