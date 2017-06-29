package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFAApplicant;
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
public class RFAApplicantDao extends RFAExternalEntityDao<RFAApplicant> {

  private static final Logger LOG = LoggerFactory.getLogger(RFAApplicantDao.class);

  @Inject
  public RFAApplicantDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  @Override
  public List<RFAApplicant> findAllByFormId(Long formId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<RFAApplicant> query =
        session.createNamedQuery(RFAApplicant.NAMED_QUERY_FIND_ALL_BY_FORM, RFAApplicant.class);
    query.setParameter(RFAApplicant.PARAM_FORM_ID, formId);
    ImmutableList.Builder<RFAApplicant> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  @Override
  public RFAApplicant findEntityByFormIdAndEntityId(Long formId, Long applicantId) {
    Session session = getSessionFactory().getCurrentSession();
    Class<RFAApplicant> entityClass = getEntityClass();
    Query<RFAApplicant> query =
        session.createNamedQuery(
            RFAApplicant.NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID, entityClass);
    query.setParameter(RFAApplicant.PARAM_FORM_ID, formId);
    query.setParameter(RFAApplicant.PARAM_APPLICANT_ID, applicantId);
    RFAApplicant res = null;
    try {
      res = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn("There is no result for formId: {} and applicantId: {}", formId, applicantId);
      LOG.debug("There is no result", e);
    }
    return res;
  }

  @Override
  public RFAApplicant deleteApplicant(Long formId, Long applicantId) {
    RFAApplicant applicant = findEntityByFormIdAndEntityId(formId, applicantId);
    if (applicant != null) {
      applicant = delete(applicant.getId());
    }
    return applicant;
  }
}
