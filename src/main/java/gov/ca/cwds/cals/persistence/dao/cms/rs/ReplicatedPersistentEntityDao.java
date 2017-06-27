package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPersistentEntity;
import gov.ca.cwds.cals.persistence.dao.stream.QueryCreator;
import gov.ca.cwds.cals.persistence.dao.stream.ReplicatedResultsStreamer;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.stream.Stream;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedPersistentEntityDao extends BaseDaoImpl<ReplicatedPersistentEntity> {

  @Inject
  public ReplicatedPersistentEntityDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @SuppressWarnings("unchecked") // because of getNamedNativeQuery
  public Stream<ReplicatedPersistentEntity> streamUpdatedFacilityData(final FacilityParameterObject parameterObject) {
    QueryCreator<ReplicatedPersistentEntity> queryCreator = (session, entityClass) -> session
        .getNamedNativeQuery(entityClass.getSimpleName() + ".findUpdatedFacilityReplicationData")
        .setParameter("dateAfter", parameterObject.getAfter())
        .setReadOnly(true);
    return new ReplicatedResultsStreamer(this, queryCreator).createStream();
  }
}
