package gov.ca.cwds.cals.service.validation;

import com.google.inject.Injector;
import com.google.inject.Key;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.util.Collection;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/** @author CWDS CALS API Team */
public class ReferentialIntegrityForEachValidator extends AbstractReferentialIntegrityValidator
    implements ConstraintValidator<
        CheckReferentialIntegrity, Collection<? extends PersistentObject>> {

  private SessionFactory sessionFactory;
  private boolean checkEquality;

  public void initialize(CheckReferentialIntegrity constraint) {
    checkEquality = constraint.checkEquality();
    Injector injector = InjectorHolder.INSTANCE.getInjector();
    sessionFactory =
        injector.getInstance(Key.get(SessionFactory.class, CalsnsSessionFactory.class));
  }

  public boolean isValid(
      Collection<? extends PersistentObject> collection, final ConstraintValidatorContext context) {
    if (collection == null || collection.isEmpty()) {
      return true;
    }

    Session currentSession = null;
    try {
      currentSession = sessionFactory.openSession();
      Session finalCurrentSession = currentSession;
      boolean[] result = new boolean[]{true};
      collection.forEach(
          o -> {
            boolean valid = checkReferentialIntegrity(finalCurrentSession, o);
            result[0] &= valid;
            if (!valid) {
              context
                  .buildConstraintViolationWithTemplate(
                      context.getDefaultConstraintMessageTemplate())
                  .addConstraintViolation();
            }
          });
      return result[0];
    } finally {
      if (currentSession != null) {
        currentSession.close();
      }
    }
  }

  @Override
  boolean isCheckEqualityRequired() {
    return checkEquality;
  }
}
