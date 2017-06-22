package gov.ca.cwds.cals.persistence.dao.cms.rs;

import com.google.inject.Inject;
import gov.ca.cwds.cals.persistence.dao.QueryCreator;
import gov.ca.cwds.cals.persistence.dao.ScalarResultsStreamer;
import gov.ca.cwds.cals.persistence.dao.cms.IClientDao;
import gov.ca.cwds.cals.persistence.model.cms.rs.ReplicatedClient;
import gov.ca.cwds.cals.web.rest.parameter.FacilityChildParameterObject;
import gov.ca.cwds.data.BaseDaoImpl;
import gov.ca.cwds.inject.CmsSessionFactory;
import java.util.stream.Stream;
import org.hibernate.SessionFactory;

/**
 * @author CWDS TPT-2
 */
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
  public Stream<ReplicatedClient> stream(FacilityChildParameterObject parameterObject) {
    QueryCreator<ReplicatedClient> queryCreator = (session, entityClass) -> session
        .createNamedQuery(entityClass.getSimpleName() + ".findUpdated", entityClass)
        .setParameter("dateAfter", parameterObject.getAfter());
    return new ScalarResultsStreamer<>(this, queryCreator).createStream();
  }
}
