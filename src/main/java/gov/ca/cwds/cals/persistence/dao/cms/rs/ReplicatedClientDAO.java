package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.cms.IClientDao;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedClient;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.Collection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/** @author CWDS CALS API Team */
public class ReplicatedClientDAO extends BaseDaoImpl<ReplicatedClient> implements
    IClientDao<ReplicatedClient> {

  @Inject
  public ReplicatedClientDAO(@CmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public ReplicatedClient findByParameterObject(FacilityChildParameterObject parameterObject) {
    throw new UnsupportedOperationException();
  }

  @Override
  public Collection<ReplicatedClient> findCollection(FacilityChildParameterObject parameterObject) {
    Session session = getSessionFactory().getCurrentSession();
    Class<ReplicatedClient> entityClass = getEntityClass();
    Query<ReplicatedClient> query =
        session.createNamedQuery(entityClass.getSimpleName() + ".findAll", entityClass);
    query.setParameter("licenseNumber", parameterObject.getLicenseNumber());
    ImmutableList.Builder<ReplicatedClient> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }
}
