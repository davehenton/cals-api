package gov.ca.cwds.cals.service.validation;

import com.google.inject.Injector;
import com.google.inject.Key;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.inject.InjectorHolder;
import gov.ca.cwds.data.persistence.PersistentObject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/** @author CWDS CALS API Team */
public class ReferentialIntegrityValidator extends AbstractReferentialIntegrityValidator
    implements ConstraintValidator<CheckReferentialIntegrity, PersistentObject> {

  private SessionFactory sessionFactory;
  private boolean checkEquality;

  public void initialize(CheckReferentialIntegrity constraint) {
    checkEquality = constraint.checkEquality();
    Injector injector = InjectorHolder.INSTANCE.getInjector();
    sessionFactory =
        injector.getInstance(Key.get(SessionFactory.class, CalsnsSessionFactory.class));
  }

  public boolean isValid(PersistentObject obj, ConstraintValidatorContext context) {
    if (obj == null) {
      return true;
    }
    Session currentSession = null;
    try {
      currentSession = sessionFactory.openSession();
      boolean valid = checkReferentialIntegrity(currentSession, obj);
      if (!valid) {
        StringBuilder sb =
            new StringBuilder(" object: ")
                .append(obj)
                .append("  is not found in DataBase referential integrity is not confirmed.");

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(sb.toString()).addConstraintViolation();
      }
      return valid;
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
