package gov.ca.cwds.cals.service.validation;

import gov.ca.cwds.data.persistence.PersistentObject;
import org.hibernate.Session;

/**
 * @author CWDS CALS API Team
 */
public abstract class AbstractReferentialIntegrityValidator {

  protected <T extends PersistentObject> boolean checkReferentialIntegrity(
      Session currentSession, T obj) {

    PersistentObject found = currentSession.get(obj.getClass(), obj.getPrimaryKey());

    boolean valid = found != null;
    if (valid && isCheckEqualityRequired()) {
      valid &= found.equals(obj);
    }
    return valid;
  }

  abstract boolean isCheckEqualityRequired();

}
