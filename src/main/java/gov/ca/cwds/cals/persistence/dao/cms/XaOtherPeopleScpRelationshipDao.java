package gov.ca.cwds.cals.persistence.dao.cms;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.XaCmsSessionFactory;
import gov.ca.cwds.cals.persistence.model.cms.OtherPeopleScpRelationship;
import gov.ca.cwds.data.BaseDaoImpl;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */
public class XaOtherPeopleScpRelationshipDao extends BaseDaoImpl<OtherPeopleScpRelationship> {

  @Inject
  public XaOtherPeopleScpRelationshipDao(@XaCmsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

}
