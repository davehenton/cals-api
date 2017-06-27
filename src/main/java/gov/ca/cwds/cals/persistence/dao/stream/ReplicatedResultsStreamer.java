package gov.ca.cwds.cals.persistence.dao.stream;

import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPersistentEntity;
import gov.ca.cwds.data.BaseDaoImpl;

/**
 * @author CWDS TPT-2
 */
public final class ReplicatedResultsStreamer extends ScalarResultsStreamer<ReplicatedPersistentEntity> {
  public ReplicatedResultsStreamer(BaseDaoImpl<ReplicatedPersistentEntity> dao, QueryCreator<ReplicatedPersistentEntity> queryCreator) {
    super(dao, queryCreator);
  }
}
