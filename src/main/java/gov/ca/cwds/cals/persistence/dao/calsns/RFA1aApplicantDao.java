package gov.ca.cwds.cals.persistence.dao.calsns;

import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import gov.ca.cwds.cals.inject.CalsnsSessionFactory;
import gov.ca.cwds.cals.persistence.model.calsns.rfa.RFA1aApplicant;
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
public class RFA1aApplicantDao extends BaseDaoImpl<RFA1aApplicant> {

  private static final Logger LOG = LoggerFactory.getLogger(RFA1aApplicantDao.class);

  @Inject
  public RFA1aApplicantDao(@CalsnsSessionFactory SessionFactory sessionFactory) {
    super(sessionFactory);
  }

  public List<RFA1aApplicant> findAllByFormId(Long formId) {
    Session session = this.getSessionFactory().getCurrentSession();
    Query<RFA1aApplicant> query =
        session.createNamedQuery(RFA1aApplicant.NAMED_QUERY_FIND_ALL_BY_FORM, RFA1aApplicant.class);
    query.setParameter(RFA1aApplicant.PARAM_FORM_ID, formId);
    ImmutableList.Builder<RFA1aApplicant> entities = new ImmutableList.Builder<>();
    entities.addAll(query.list());
    return entities.build();
  }

  public RFA1aApplicant findApplicantByFormIdAndApplicantId(Long formId, Long applicantId) {
    Session session = getSessionFactory().getCurrentSession();
    Class<RFA1aApplicant> entityClass = getEntityClass();
    Query<RFA1aApplicant> query =
        session.createNamedQuery(
            RFA1aApplicant.NAMED_QUERY_FIND_BY_FORM_ID_AND_APPLICANT_ID, entityClass);
    query.setParameter(RFA1aApplicant.PARAM_FORM_ID, formId);
    query.setParameter(RFA1aApplicant.PARAM_APPLICANT_ID, applicantId);
    RFA1aApplicant res = null;
    try {
      res = query.getSingleResult();
    } catch (NoResultException e) {
      LOG.warn("There is no result for formId: {} and applicantId: {}", formId, applicantId);
      LOG.debug(e.getMessage(), e);
    }
    return res;
  }

  public RFA1aApplicant deleteApplicant(Long formId, Long applicantId) {
    RFA1aApplicant applicant = findApplicantByFormIdAndApplicantId(formId, applicantId);
    if (applicant != null) {
      applicant = delete(applicantId);
    }
    return applicant;
  }
}
