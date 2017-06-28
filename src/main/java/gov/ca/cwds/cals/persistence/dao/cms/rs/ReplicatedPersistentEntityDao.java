package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.RecordChangeObject;
import gov.ca.cwds.cals.persistence.dao.stream.QueryCreator;
import gov.ca.cwds.cals.persistence.dao.stream.RecordChangesStreamer;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.Date;
import java.util.stream.Stream;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedPersistentEntityDao extends BaseDaoImpl<RecordChangeObject> {

  @Inject
  public ReplicatedPersistentEntityDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @SuppressWarnings("unchecked") // because of getNamedNativeQuery
  public Stream<RecordChangeObject> streamChangedFacilityRecords(final Date after) {
    QueryCreator<RecordChangeObject> queryCreator = (session, entityClass) -> session
        .getNamedNativeQuery(entityClass.getSimpleName() + ".findChangedFacilityRecords")
        .setParameter("dateAfter", after).setReadOnly(true);
    return new RecordChangesStreamer(this, queryCreator).createStream();
  }
}
