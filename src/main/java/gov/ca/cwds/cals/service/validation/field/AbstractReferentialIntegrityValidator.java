package gov.ca.cwds.cals.service.validation.field;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import org.hibernate.Session;

/**
 * @author CWDS CALS API Team
 */
public abstract class AbstractReferentialIntegrityValidator {

  protected <T extends PersistentObject> boolean checkReferentialIntegrity(
      Session currentSession, T obj) {
    PersistentObject found = null;
    Serializable primaryKey = obj.getPrimaryKey();
    if (primaryKey != null) {
      found = currentSession.get(obj.getClass(), primaryKey);
    }
    boolean valid = found != null;
    if (valid && isCheckEqualityRequired()) {
      valid = found.equals(obj);
    }
    return valid;
  }

  abstract boolean isCheckEqualityRequired();

}
