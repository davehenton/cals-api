package gov.ca.cwds.cals.persistence.dao.calsns;

import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

public abstract class RFAExternalEntityDao<T extends RFAExternalEntity> extends
    BaseDaoImpl<T> {

  public RFAExternalEntityDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public abstract List<T> findAllByFormId(Long formId);

  public abstract T findEntityByFormIdAndEntityId(Long formId, Long entityId);

  public abstract T deleteApplicant(Long formId, Long entityId);

}
