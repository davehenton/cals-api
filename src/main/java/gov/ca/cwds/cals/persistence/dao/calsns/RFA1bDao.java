package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1bForm;
import gov.ca.cwds.cals.service.rfa.factory.RFA1bFactory;
import java.io.Serializable;
import java.util.Optional;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public class RFA1bDao extends RFAExternalEntityDao<RFA1bForm> {

  @Inject
  RFA1bDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory, RFA1bFactory.INSTANCE);
  }

  @Override
  public RFA1bForm delete(Serializable id) {
    RFA1bForm entity = this.find(id);
    Optional.ofNullable(entity).ifPresent(e -> {
      this.currentSession().delete(e);
      afterDelete(entity);
    });
    
    return entity;
  }

  /**
   * Override this method if any action need to be performed after deletion
   * @param deleted RFA1bForm which was deleted
   */
  protected void afterDelete(RFA1bForm deleted) {
    //Do nothing
  }

}
