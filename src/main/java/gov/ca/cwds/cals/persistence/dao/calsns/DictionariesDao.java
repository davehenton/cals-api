package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.AgeGroupType;
import gov.ca.cwds.cals.persistence.model.calsns.Dictionary;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/** @author CWDS CALS API Team */
public class DictionariesDao extends BaseDaoImpl<AgeGroupType> {

  @Inject
  public DictionariesDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<Dictionary> findDictionariesByType(String type) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<Dictionary> query =
        session.createNamedQuery(Dictionary.NQ_FIND_BY_TYPE, Dictionary.class);

    query.setParameter("type", type);
    ImmutableList.Builder<Dictionary> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }
}
