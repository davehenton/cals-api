package gov.ca.cwds.cals.inject;

import com.google.inject.BindingAnnotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 *  Annotation for binding to our Facility backend resource
 *
 *  @author CALS API Team
 *
 */
@BindingAnnotation
@Target({FIELD, PARAMETER, METHOD})
@Retention(RUNTIME)
public @interface FacilityServiceBackendResource {

}
