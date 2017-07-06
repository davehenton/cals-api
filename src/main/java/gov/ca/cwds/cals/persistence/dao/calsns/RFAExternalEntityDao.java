package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAExternalEntity;
import gov.ca.cwds.cals.service.dto.rfa.RFAExternalEntityDTO;
import gov.ca.cwds.cals.service.rfa.factory.RFAExternalEntityFactory;
import gov.ca.cwds.data.BaseDaoImpl;
import java.util.List;
import javax.persistence.NoResultException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author CWDS CALS API Team
 */
public abstract class RFAExternalEntityDao<
    T extends RFAExternalEntity,
    D extends RFAExternalEntityDTO>
    extends BaseDaoImpl<T> {

  private static final Logger LOG = LoggerFactory.getLogger(RFAExternalEntityDao.class);

  private RFAExternalEntityFactory<T, D> entityFactory;

  public RFAExternalEntityDao(
      @CalsnsSessionFactory SessionFactory sessionFactory,
      RFAExternalEntityFactory<T, D> entityFactory) {
    super(sessionFactory);
    this.entityFactory = entityFactory;
  }

  public List<T> findAllByFormId(Long formId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<T> query =
        session.createNamedQuery(
            entityFactory.getFindAllByFormNamedQuery(), entityFactory.getEntityClass());
    query.setParameter(RFAExternalEntity.PARAM_FORM_ID, formId);
    ImmutableList.Builder<T> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  public T findEntityByFormIdAndEntityId(Long formId, Long entityId) {
    Session session = getSessionFactory().getCurrentSession();
    Query<T> query =
        session.createNamedQuery(
            entityFactory.getFindByFormIdAndEntityIdNamedQuery(), entityFactory.getEntityClass());
    query.setParameter(RFAExternalEntity.PARAM_FORM_ID, formId);
    query.setParameter(RFAExternalEntity.PARAM_ENTITY_ID, entityId);
    T res = null;
    try {
      res = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn("There is no result for formId: {} and entityId: {}", formId, entityId);
      LOG.debug("There is no result", e);
    }
    return res;
  }

  public T deleteApplicant(Long formId, Long entityId) {
    T entity = findEntityByFormIdAndEntityId(formId, entityId);
    if (entity != null) {
      entity = delete(entity.getId());
    }
    return entity;
  }
}
