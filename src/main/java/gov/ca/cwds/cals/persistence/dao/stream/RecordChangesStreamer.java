package gov.ca.cwds.cals.persistence.dao.stream;

import gov.ca.cwds.cals.persistence.model.RecordChange;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.data.stream.QueryCreator;
import gov.ca.cwds.data.stream.ScalarResultsStreamer;

/**
 * @author CWDS TPT-2
 */
public final class RecordChangesStreamer extends ScalarResultsStreamer<RecordChange> {
  public RecordChangesStreamer(BaseDaoImpl<RecordChange> dao, QueryCreator<RecordChange> queryCreator) {
    super(dao, queryCreator);
  }
}
