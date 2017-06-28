package gov.ca.cwds.cals.persistence.dao.stream;

import gov.ca.cwds.cals.persistence.model.RecordChangeObject;
import gov.ca.cwds.data.BaseDaoImpl;

/**
 * @author CWDS TPT-2
 */
public final class RecordChangesStreamer extends ScalarResultsStreamer<RecordChangeObject> {
  public RecordChangesStreamer(BaseDaoImpl<RecordChangeObject> dao, QueryCreator<RecordChangeObject> queryCreator) {
    super(dao, queryCreator);
  }
}
