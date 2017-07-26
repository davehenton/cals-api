package gov.ca.cwds.cals.service.validation.field;

import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.util.Collection;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;

/**
 * @author CWDS CALS API Team
 */
public class ReferentialIntegrityForEachValidator extends AbstractReferentialIntegrityValidator
    implements ConstraintValidator<
    CheckReferentialIntegrityForEach, Collection<? extends PersistentObject>>,
    CalsSessionFactoryAware {

  private boolean checkEquality;

  public void initialize(CheckReferentialIntegrityForEach constraint) {
    checkEquality = constraint.checkEquality();
  }

  public boolean isValid(
      Collection<? extends PersistentObject> collection, final ConstraintValidatorContext context) {
    if (collection == null || collection.isEmpty()) {
      return true;
    }

    try (Session currentSession = openSession()) {
      boolean[] result = new boolean[]{true};
      int[] index = new int[]{0};
      collection.forEach(
          o -> {
            boolean valid = checkReferentialIntegrity(currentSession, o);
            result[0] &= valid;
            if (!valid) {
              context.disableDefaultConstraintViolation();
              StringBuilder sb = new StringBuilder();
              sb.append('[')
                  .append(index[0])
                  .append("] object ")
                  .append(o)
                  .append("  is not found in DataBase ");
              context.buildConstraintViolationWithTemplate(sb.toString()).addConstraintViolation();
            }
            index[0]++;
          });
      return result[0];
    }
  }

  @Override
  boolean isCheckEqualityRequired() {
    return checkEquality;
  }
}
