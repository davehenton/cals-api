package gov.ca.cwds.cals.persistence.dao;

import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.persistence.PersistentObject;
import org.hibernate.ScrollableResults;

/**
 * @author CWDS TPT-2
 */
public final class ScalarResultsStreamer<T extends PersistentObject> extends
    AbstractResultsStreamer<T> {

  public ScalarResultsStreamer(BaseDaoImpl<T> dao, QueryCreator<T> queryCreator) {
    super(dao, queryCreator);
  }

  @SuppressWarnings("unchecked")
  protected final T getResult(ScrollableResults scrollableResults) {
    return (T) scrollableResults.get(0);
  }
}
