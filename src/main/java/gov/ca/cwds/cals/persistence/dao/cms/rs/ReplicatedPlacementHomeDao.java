package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.QueryCreator;
import gov.ca.cwds.cals.persistence.dao.ScalarResultsStreamer;
import gov.ca.cwds.cals.persistence.dao.cms.IPlacementHomeDao;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPlacementHome;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.stream.Stream;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedPlacementHomeDao extends BaseDaoImpl<ReplicatedPlacementHome> implements
    IPlacementHomeDao<ReplicatedPlacementHome> {

  /**
   * Constructor
   *
   * @param sessionFactory The sessionFactory
   */
  @Inject
  public ReplicatedPlacementHomeDao(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public ReplicatedPlacementHome findByParameterObject(FacilityParameterObject parameterObject) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Stream<ReplicatedPlacementHome> stream(final FacilityParameterObject parameterObject) {
    QueryCreator<ReplicatedPlacementHome> queryCreator = (session, entityClass) -> session
        .createNamedQuery(entityClass.getSimpleName() + ".findUpdated", entityClass)
        .setParameter("dateAfter", parameterObject.getAfter())
        .setReadOnly(true);
    return new ScalarResultsStreamer<>(this, queryCreator).createStream();
  }
}
