package gov.ca.cwds.cals.service.validation.field;

import static gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType.NAMED_QUERY_FIND_STATE_BY_CODE;

import gov.ca.cwds.cals.Constants;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.StateType;
import gov.ca.cwds.cals.service.validation.CalsSessionFactoryAware;
import gov.ca.cwds.data.persistence.PersistentObject;
import java.io.Serializable;
import java.util.Collection;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public class StateReferentialIntegrityForEachValidator extends AbstractReferentialIntegrityValidator
    implements ConstraintValidator<
    CheckStateReferentialIntegrityForEach, Collection<? extends PersistentObject>>,
    CalsSessionFactoryAware {

  private static final Logger LOG = LoggerFactory
      .getLogger(StateReferentialIntegrityForEachValidator.class);

  private boolean checkEquality;
  private boolean enrich;

  public void initialize(CheckStateReferentialIntegrityForEach constraint) {
    checkEquality = constraint.checkEquality();
    enrich = constraint.enrich();
  }

  @Override
  protected  <T extends PersistentObject> PersistentObject getPersistentObject(Session currentSession,
      T obj, Serializable primaryKey) {
    PersistentObject found;
    Query<StateType> query =
        currentSession.createNamedQuery(NAMED_QUERY_FIND_STATE_BY_CODE, StateType.class);
    query.setParameter("stateCode", primaryKey);
    found = query.uniqueResult();
    return found;
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
              context.buildConstraintViolationWithTemplate(
                  String.format(Constants.Validation.Field.REFERENTIAL_INTEGRITY_LIST_MESSAGE,
                      index[0], o))
                  .addConstraintViolation();
            }
            index[0]++;
          });
      return result[0];
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
