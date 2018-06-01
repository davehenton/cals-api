package gov.ca.cwds.cals.persistence.dao.calsns;

import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.tracking.TrackingTemplate;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * TrackingTemplate Data Access Object.
 *
 * @author CWDS TPT-2 Team
 */
public class TrackingTemplateDao extends BaseDaoImpl<TrackingTemplate> {
  public TrackingTemplateDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }
}
