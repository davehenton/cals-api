package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAMinorChild;
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
public class RFAMinorChildDao extends BaseDaoImpl<RFAMinorChild> {

  private static final Logger LOG = LoggerFactory.getLogger(RFAMinorChildDao.class);

  @Inject
  public RFAMinorChildDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public RFAMinorChild findMinorChildByFormIdAndMinorChildId(
      Long applicationId, Long minorChildId) {
    Session session = getSessionFactory().getCurrentSession();
    Class<RFAMinorChild> entityClass = getEntityClass();
    Query<RFAMinorChild> query =
        session.createNamedQuery(
            RFAMinorChild.NAMED_QUERY_FIND_BY_FORM_ID_AND_CHILD_ID, entityClass);
    query.setParameter(RFAMinorChild.PARAM_FORM_ID, applicationId);
    query.setParameter(RFAMinorChild.PARAM_CHILD_ID, minorChildId);
    RFAMinorChild res = null;
    try {
      res = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn(
          "There is no result for formId: {} and minorChildId: {}", applicationId, minorChildId);
      LOG.debug(e.getMessage(), e);
    }
    return res;
  }

  public RFAMinorChild deleteMinor(Long applicationId, Long minorChildId) {
    RFAMinorChild minorChildEntity = findMinorChildByFormIdAndMinorChildId(applicationId,
        minorChildId);
    if (minorChildEntity != null) {
      minorChildEntity = delete(minorChildEntity.getId());
    }
    return minorChildEntity;
  }

  public List<RFAMinorChild> findAllByFormId(Long formId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<RFAMinorChild> query =
        session
            .createNamedQuery(RFAMinorChild.NAMED_QUERY_FIND_ALL_BY_FORM, RFAMinorChild.class);
    query.setParameter(RFAMinorChild.PARAM_FORM_ID, formId);
    ImmutableList.Builder<RFAMinorChild> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }
}
