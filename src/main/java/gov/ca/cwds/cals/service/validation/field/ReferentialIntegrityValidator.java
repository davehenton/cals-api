package gov.ca.cwds.cals.service.validation.field;

import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.data.persistence.PersistentObject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;

/**
 * @author CWDS CALS API Team
 */
public class ReferentialIntegrityValidator extends AbstractReferentialIntegrityValidator
    implements ConstraintValidator<CheckReferentialIntegrity, PersistentObject>,
    CalsSessionFactoryAware {

  private static final String VALIDATION_MESSAGE =
      " Object %s is not found in database. Referential integrity was not confirmed.";

  private boolean checkEquality;

  public void initialize(CheckReferentialIntegrity constraint) {
    checkEquality = constraint.checkEquality();
  }

  public boolean isValid(PersistentObject obj, ConstraintValidatorContext context) {
    if (obj == null) {
      return true;
    }

    try (Session currentSession = openSession()) {

      if (!checkReferentialIntegrity(currentSession, obj)) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(String.format(VALIDATION_MESSAGE, obj))
            .addConstraintViolation();
        return false;
      }
      return true;
    }
  }

  @Override
  boolean isCheckEqualityRequired() {
    return checkEquality;
  }
}
