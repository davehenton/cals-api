package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.Constants.DictionaryType;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.BaseDictionary;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * @author CWDS CALS API Team
 */
public class DictionariesDao extends BaseDaoImpl<BaseDictionary> {

  @Inject
  public DictionariesDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<BaseDictionary> findDictionariesByType(DictionaryType type) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<? extends BaseDictionary> query =
        session.createNamedQuery(
            BaseDictionary.buildFindAllQueryName(type.getDictionaryClass()),
            type.getDictionaryClass());
    query.setCacheable(true).setCacheRegion("dictionaries");
    ImmutableList.Builder<BaseDictionary> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  public <T extends BaseDictionary> List<T> findDictionariesByType(Class<T> clazz) {
    Session session = getSessionFactory().getCurrentSession();
    Query<T> query = session.createNamedQuery(BaseDictionary.buildFindAllQueryName(clazz), clazz);
    query.setCacheable(true).setCacheRegion("dictionaries");
    ImmutableList.Builder<T> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }
}
