package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.dictionaries.CountyType;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 * TrackingTemplate Data Access Object.
 *
 * @author CWDS TPT-2 Team
 */
public class TrackingTemplateDao extends CalsBaseEntityDao<TrackingTemplate> {

  @Inject
  public TrackingTemplateDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  /**
   * Finds TrackingTemplate by type.
   *
   * @param type Template type
   * @return TrackingTemplate
   */
  public TrackingTemplate findByType(String type) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<TrackingTemplate> query =
        session.createNamedQuery(TrackingTemplate.NAMED_QUERY_FIND_BY_TYPE, TrackingTemplate.class);
    query.setParameter("type", type);
    return query.uniqueResult();
  }

  /**
   * Finds TrackingTemplate by county type.
   *
   * @param county CountyType
   * @param type Template type
   * @return TrackingTemplate
   */
  public TrackingTemplate findByCountyAndType(CountyType county, String type) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<TrackingTemplate> query =
        session.createNamedQuery(TrackingTemplate.NAMED_QUERY_FIND_BY_COUNTY_AND_TYPE,
            TrackingTemplate.class);
    query.setParameter("county", county);
    query.setParameter("type", type);
    return query.uniqueResult();
  }

}
