package gov.ca.cwds.cals.service.validation.field;

import gov.ca.cwds.cals.Constants;
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
        context.buildConstraintViolationWithTemplate(
            String.format(Constants.FieldValidation.REFERENTIAL_INTEGRITY_MESSAGE_TEMPLATE, obj))
            .addConstraintViolation();
        return false;
      }
      return true;
    } catch (Exception e) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
          Constants.FieldValidation.CANNOT_OPEN_DATABASE_SESSION_MESSAGE_TEMPLATE)
          .addConstraintViolation();
      return false;
    }
  }

  @Override
  boolean isCheckEqualityRequired() {
    return checkEquality;
  }
}
