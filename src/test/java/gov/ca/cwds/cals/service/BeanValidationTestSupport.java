package gov.ca.cwds.cals.service;

import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.dto.rfa.PhoneDTO;
import org.junit.Before;

/**
 * @author CWDS CALS API Team
 */
public class BeanValidationTestSupport<T> {

  private Validator validator;

  @Before
  public void init() {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    this.validator = factory.getValidator();
  }

  protected Set<ConstraintViolation<T>> validate(T bean) {
    return this.validator.validate(bean);
  }

  public Set<ConstraintViolation<PhoneDTO>> removeDbSessionViolation(Set<ConstraintViolation<PhoneDTO>> violations) {
    return violations.stream()
          .filter(b -> !b.getMessageTemplate().equals(
              Constants.Validation.Field.CANNOT_OPEN_DATABASE_SESSION_MESSAGE))
          .collect(Collectors.toSet());
  }
}
