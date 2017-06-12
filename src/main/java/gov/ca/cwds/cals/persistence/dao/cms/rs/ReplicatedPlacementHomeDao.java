package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.IPlacementHomeDao;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedPlacementHome;
import gov.ca.cwds.cals.web.rest.parameter.FacilityParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS TPT-2
 */
public class ReplicatedPlacementHomeDao extends BaseDaoImpl<ReplicatedPlacementHome> implements
    IPlacementHomeDao<ReplicatedPlacementHome> {
  private static final Logger LOG = LoggerFactory.getLogger(ReplicatedPlacementHomeDao.class);

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

  // todo pagination
  @Override
  public Collection<ReplicatedPlacementHome> findCollection(FacilityParameterObject parameterObject) {
    Session session = this.getSessionFactory().getCurrentSession();
    Class<ReplicatedPlacementHome> entityClass = this.getEntityClass();

    Query<ReplicatedPlacementHome> query = session.createNamedQuery(entityClass.getSimpleName() + ".findUpdated", entityClass);

    return query.getResultList();
  }
}
