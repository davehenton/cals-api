package gov.ca.cwds.cals.persistence.dao.fas;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.LisSessionFactory;
import gov.ca.cwds.cals.persistence.dao.stream.QueryCreator;
import gov.ca.cwds.cals.persistence.dao.stream.RecordChangesStreamer;
import gov.ca.cwds.cals.persistence.model.RecordChange;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.Date;
import java.util.stream.Stream;
import org.hibernate.SessionFactory;

/** @author CWDS CALS API Team */
public class RecordChangeFasDao extends BaseDaoImpl<RecordChange> {

  @Inject
  public RecordChangeFasDao(@LisSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @SuppressWarnings("unchecked") // because of getNamedNativeQuery
  public Stream<RecordChange> streamChangedFacilityRecords(final Date after) {
    QueryCreator<RecordChange> queryCreator = (session, entityClass) -> session
        .getNamedNativeQuery(entityClass.getSimpleName() + ".findChangedFacilityRecordsInFAS")
        .setParameter("dateAfter", after).setReadOnly(true);
    return new RecordChangesStreamer(this, queryCreator).createStream();
  }
}
