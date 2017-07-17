package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.FasSessionFactory;
import gov.ca.cwds.cals.persistence.dao.stream.QueryCreator;
import gov.ca.cwds.cals.persistence.dao.stream.RecordChangesStreamer;
import gov.ca.cwds.cals.persistence.model.RecordChange;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.Date;
import java.util.stream.Stream;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class RecordChangeFasDao extends BaseDaoImpl<RecordChange> {

  @Inject
  public RecordChangeFasDao(@FasSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @SuppressWarnings("unchecked") // because of getNamedNativeQuery
  public Stream<RecordChange> streamChangedFacilityRecords(final boolean initialLoad,
      final Date after) {
    QueryCreator<RecordChange> queryCreator = (session, entityClass) -> session
        .getNamedNativeQuery(entityClass.getSimpleName() + ".findChangedFacilityRecordsInFAS")
        .setParameter("initialLoad", initialLoad ? 1 : 0)
        .setParameter("dateAfter", after)
        .setReadOnly(true);
    return new RecordChangesStreamer(this, queryCreator).createStream();
  }
}
