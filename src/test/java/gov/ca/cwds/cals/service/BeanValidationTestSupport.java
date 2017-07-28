package gov.ca.cwds.cals.service;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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

}
