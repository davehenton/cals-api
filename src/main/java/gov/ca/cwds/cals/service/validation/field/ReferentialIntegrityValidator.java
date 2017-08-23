package gov.ca.cwds.cals.service.validation.field;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.data.persistence.PersistentObject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class ReferentialIntegrityValidator extends AbstractReferentialIntegrityValidator
    implements ConstraintValidator<CheckReferentialIntegrity, PersistentObject>,
    CalsSessionFactoryAware {

  private static final Logger LOG = LoggerFactory.getLogger(ReferentialIntegrityValidator.class);

  private boolean checkEquality;
  private boolean enrich;

  public void initialize(CheckReferentialIntegrity constraint) {
    checkEquality = constraint.checkEquality();
    enrich = constraint.enrich();
  }

  public boolean isValid(PersistentObject obj, ConstraintValidatorContext context) {
    if (obj == null) {
      return true;
    }

    try (Session currentSession = openSession()) {

      if (!checkReferentialIntegrity(currentSession, obj)) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
            String.format(Constants.Validation.Field.REFERENTIAL_INTEGRITY_MESSAGE, obj))
            .addConstraintViolation();
        return false;
      }
      return true;
    } catch (Exception e) {
      LOG.error("Can't get Hibernate session", e);
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
          Constants.Validation.Field.CANNOT_OPEN_DATABASE_SESSION_MESSAGE)
          .addConstraintViolation();
      return false;
    }
  }

  @Override
  boolean isCheckEqualityRequired() {
    return checkEquality;
  }

  @Override
  boolean isEnrichmentRequired() {
    return enrich;
  }
}
