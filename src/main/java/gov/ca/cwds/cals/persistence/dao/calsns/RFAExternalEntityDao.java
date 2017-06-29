package gov.ca.cwds.cals.persistence.dao.calsns;

import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 * @author CWDS CALS API Team
 */

@SuppressWarnings("squid:S00119")
public abstract class RFAExternalEntityDao<Entity extends RFAExternalEntity> extends
    BaseDaoImpl<Entity> {

  public RFAExternalEntityDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public abstract List<Entity> findAllByFormId(Long formId);

  public abstract Entity findEntityByFormIdAndEntityId(Long formId, Long entityId);

  public abstract Entity deleteApplicant(Long formId, Long entityId);

}
