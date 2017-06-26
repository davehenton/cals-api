package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aMinorChild;
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
public class RFA1aMinorChildDao extends BaseDaoImpl<RFA1aMinorChild> {

  private static final Logger LOG = LoggerFactory.getLogger(RFA1aMinorChildDao.class);

  @Inject
  public RFA1aMinorChildDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public RFA1aMinorChild findMinorChildByFormIdAndMinorChildId(
      Long applicationId, Long minorChildId) {
    Session session = getSessionFactory().getCurrentSession();
    Class<RFA1aMinorChild> entityClass = getEntityClass();
    Query<RFA1aMinorChild> query =
        session.createNamedQuery(
            RFA1aMinorChild.NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID, entityClass);
    query.setParameter(RFA1aMinorChild.PARAM_FORM_ID, applicationId);
    query.setParameter(RFA1aMinorChild.PARAM_CHILD_ID, minorChildId);
    RFA1aMinorChild res = null;
    try {
      res = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn(
          "There is no result for formId: {} and minorChildId: {}", applicationId, minorChildId);
      LOG.debug(e.getMessage(), e);
    }
    return res;
  }

  public RFA1aMinorChild deleteMinor(Long applicationId, Long minorChildId) {
    RFA1aMinorChild minorChildEntity = findMinorChildByFormIdAndMinorChildId(applicationId,
        minorChildId);
    if (minorChildEntity != null) {
      minorChildEntity = delete(minorChildEntity.getId());
    }
    return minorChildEntity;
  }

  public List<RFA1aMinorChild> findAllByFormId(Long formId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<RFA1aMinorChild> query =
        session
            .createNamedQuery(RFA1aMinorChild.NAMED_QUERY_FIND_ALL_BY_FORM, RFA1aMinorChild.class);
    query.setParameter(RFA1aMinorChild.PARAM_FORM_ID, formId);
    ImmutableList.Builder<RFA1aMinorChild> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }
}
