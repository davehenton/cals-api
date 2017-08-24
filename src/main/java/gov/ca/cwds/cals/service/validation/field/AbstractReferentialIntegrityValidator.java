package gov.ca.cwds.cals.service.validation.field;

import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.hibernate.Session;

/**
 * @author CWDS CALS API Team
 */
public abstract class AbstractReferentialIntegrityValidator {

  protected <T extends PersistentObject> boolean checkReferentialIntegrity(Session currentSession,
      T obj) {
    PersistentObject found = null;
    Serializable primaryKey = obj.getPrimaryKey();

    if (primaryKey != null) {
      found = currentSession.get(obj.getClass(), primaryKey);
    }

    boolean valid = found != null;

    if (valid && isCheckEqualityRequired()) {
      valid = found.equals(obj);
    }

    if (valid && isEnrichmentRequired()) {
      List<Field> fields = getAllFields(new LinkedList<>(), obj.getClass());

      T finalFound = (T) found;
      fields.stream().filter(field -> !Modifier.isStatic(field.getModifiers()))
          .forEach(field -> {
            try {
              field.setAccessible(true);
              field.set(obj, field.get(finalFound));
            } catch (IllegalAccessException e) {
              throw new IllegalArgumentException(e);
            }
          });
    }

    return valid;
  }

  private List<Field> getAllFields(@NotNull List<Field> fields, Class<?> clazz) {
    fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
    if (clazz.getSuperclass() != null) {
      getAllFields(fields, clazz.getSuperclass());
    }

    return fields;
  }

  abstract boolean isCheckEqualityRequired();

  abstract boolean isEnrichmentRequired();
}
