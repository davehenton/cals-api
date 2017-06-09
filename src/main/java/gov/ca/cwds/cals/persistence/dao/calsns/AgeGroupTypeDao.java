package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/** @author CWDS CALS API Team */
public class AgeGroupTypeDao extends BaseDaoImpl<AgeGroupType> {

  @Inject
  public AgeGroupTypeDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<AgeGroupType> findAll() {
    Session session = this.getSessionFactory().getCurrentSession();
    org.hibernate.query.Query<AgeGroupType> query =
        session.createNamedQuery(AgeGroupType.NQ_ALL, AgeGroupType.class);
    ImmutableList.Builder<AgeGroupType> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

}
